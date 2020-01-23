package team58.healthy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team58.healthy.model.ClinicAdmin;
import team58.healthy.model.Doctor;
import team58.healthy.model.ExtendedUserDetails;
import team58.healthy.model.Patient;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private PatientService patientService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private ClinicAdminService clinicAdminService;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Override
    public ExtendedUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Patient patient = patientService.findByEmail(email);
        if (patient != null) {
            System.out.println("Found patient.");
            return patient;
        }

        Doctor doctor = doctorService.findByEmail(email);
        if (doctor != null) {
            System.out.println("Found doctor.");
            return doctor;
        }

        ClinicAdmin clinicAdmin = clinicAdminService.findByEmail(email);
        if (clinicAdmin != null) {
            System.out.println("Found clinic admin.");
            return clinicAdmin;
        }

        System.out.println("Found no matching user.");
        throw new UsernameNotFoundException(String.format("No user with email: %s", email));
    }

    public String findUserType(String email) {
        Patient patient = patientService.findByEmail(email);
        if (patient != null)
            return "patient";

        Doctor doctor = doctorService.findByEmail(email);
        if (doctor != null)
            return "doctor";

        ClinicAdmin clinicAdmin = clinicAdminService.findByEmail(email);
        if (clinicAdmin != null)
            return "clinicAdmin";

        return "";
    }

    public void changePassword(String oldPassword, String newPassword) {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String email = currentUser.getName();

        if (authenticationManager == null) {
            System.out.println("Authentication manager not set. Cannot change password.");
            return;
        }

        System.out.println("Re-authenticating patient '" + email + "' for password change request.");
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, oldPassword));

        System.out.println("Changing password for " + email);
        Patient patient = (Patient)loadUserByUsername(email);
        patient.setPassword(passwordEncoder.encode(newPassword));

        // TODO: add calls to other services depending on authorities
        patientService.save(patient);
    }
}
