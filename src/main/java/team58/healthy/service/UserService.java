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
import team58.healthy.model.*;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private PatientService patientService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private ClinicAdminService clinicAdminService;
    @Autowired
    private ClinicCenterAdminService clinicCenterAdminService;
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

        ClinicCenterAdmin clinicCenterAdmin = clinicCenterAdminService.findByEmail(email);
        if (clinicCenterAdmin != null) {
            System.out.println("Found clinic center admin.");
            return clinicCenterAdmin;
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

        ClinicCenterAdmin clinicCenterAdmin = clinicCenterAdminService.findByEmail(email);
        if (clinicCenterAdmin != null)
            return "clinicCenterAdmin";

        return "";
    }

    public String changePassword(String oldPassword, String newPassword) {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String email = currentUser.getName();

        if (authenticationManager == null) {
            System.out.println("Authentication manager not set. Cannot change password.");
            return "Authentication manager not set. Cannot change password.";
        }

        System.out.println("Re-authenticating user '" + email + "' for password change request.");
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, oldPassword));
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred during authentication of user " + email + ".";
        }

        System.out.println("Changing password for " + email + ".");

        ExtendedUserDetails user;
        try {
            user = loadUserByUsername(email);
        } catch (UsernameNotFoundException e) {
            System.out.println("No user found for username.");
            return "No user found for username.";
        }

        if (user instanceof Patient) {
            Patient patient = (Patient)user;
            patient.setPassword(passwordEncoder.encode(newPassword));
            patientService.save(patient);
        } else if (user instanceof Doctor) {
            Doctor doctor = (Doctor) user;
            doctor.setPassword(passwordEncoder.encode(newPassword));
            doctorService.save(doctor);
        } else if (user instanceof ClinicAdmin) {
            ClinicAdmin clinicAdmin = (ClinicAdmin) user;
            clinicAdmin.setPassword(passwordEncoder.encode(newPassword));
            clinicAdminService.save(clinicAdmin);
        } else if (user instanceof ClinicCenterAdmin) {
            ClinicCenterAdmin clinicCenterAdmin = (ClinicCenterAdmin) user;
            clinicCenterAdmin.setPassword(passwordEncoder.encode(newPassword));
            clinicCenterAdminService.save(clinicCenterAdmin);
        }

        return "Password for " + user.getEmail() + " successfully changed.";
    }
}
