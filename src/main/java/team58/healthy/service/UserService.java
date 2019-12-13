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
import team58.healthy.model.ExtendedUserDetails;
import team58.healthy.model.Patient;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private PatientService patientService;
    // TODO: add other required services

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Override
    public ExtendedUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // TODO: add calls to other services
        Patient patient = patientService.findByEmail(email);
        if (patient == null)
            throw new UsernameNotFoundException(String.format("No patient with email: %s", email));
        else
            return patient;
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
