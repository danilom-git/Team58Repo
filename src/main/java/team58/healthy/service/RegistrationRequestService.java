package team58.healthy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team58.healthy.dto.RegistrationRequestDTO;
import team58.healthy.model.Patient;
import team58.healthy.model.RegistrationRequest;
import team58.healthy.repository.RegistrationRequestRepository;
import team58.healthy.security.TokenUtils;

import javax.mail.MessagingException;

@Service
public class RegistrationRequestService {

    @Autowired
    private RegistrationRequestRepository registrationRequestRepository;
    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RegistrationRequestService registrationRequestService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private TokenUtils tokenUtils;

    public RegistrationRequest save(RegistrationRequest request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        return registrationRequestRepository.save(request);
    }

    public RegistrationRequest save(RegistrationRequestDTO requestDTO) {
        requestDTO.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
        return registrationRequestRepository.save(new RegistrationRequest(requestDTO));
    }

    public String enableAccount(Long requestId) {
        RegistrationRequest request = registrationRequestService.findById(requestId);
        if (request == null)
            return "A request with the selected ID does not exist";
        if (request.isArchived())
            return "The selected request is already archived and cannot be altered.";
        if (request.isAccepted())
            return "The selected request is already accepted";

        request.setAccepted(true);
        registrationRequestRepository.save(request);

        String token = tokenUtils.generateToken(request.getEmail());

        try {
            emailService.sendHtmlMail(request.getEmail(), "Account Verification", "<html><body>Greetings " + request.getName() + "<br>"
                    + "<a href=\"http://localhost:8080/api/registration/verify/user:" + token + "/request:" + request.getId()  + "\">"
                    + "Verify your account</a></body></html>");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return "Request successfully accepted.";
    }

    public RegistrationRequest findById(Long id) {
        return registrationRequestRepository.findById(id).orElse(null);
    }

    public String verifyAccount(Long requestId, String token) {
        RegistrationRequest request = registrationRequestService.findById(requestId);
        if (request == null)
            return "A request with the selected ID does not exist.";
        if (request.isArchived())
            return "The selected request is already archived and cannot be altered.";
        if (!request.isAccepted())
            return "The selected request has not been accepted yet.";
        if (request.isVerified())
            return "The selected request is already verified.";

//        System.out.println(token);
        String patientEmail = tokenUtils.getUsernameFromToken(token);
        String requestEmail = request.getEmail();
//        System.out.println(patientEmail);
//        System.out.println(requestEmail);
        if (!patientEmail.equals(requestEmail))
            return "Invalid token.";

        request.setVerified(true);
        request.setArchived(true);
        registrationRequestRepository.save(request);

        Patient patient = new Patient(request, authorityService.findByName("ROLE_PATIENT").get(0));
        patientService.save(patient);

        return "Successfully verified account.";
    }
}
