package team58.healthy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team58.healthy.dto.RegistrationRequestDTO;
import team58.healthy.model.Patient;
import team58.healthy.model.RegistrationRequest;
import team58.healthy.repository.RegistrationRequestRepository;

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

    public RegistrationRequest save(RegistrationRequest request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        return registrationRequestRepository.save(request);
    }

    public RegistrationRequest save(RegistrationRequestDTO requestDTO) {
        requestDTO.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
        return registrationRequestRepository.save(new RegistrationRequest(requestDTO));
    }

    public void enableAccount(RegistrationRequest request) {
        request.setAccepted(true);
        request.setArchived(true);
        registrationRequestRepository.save(request);
        Patient patient = new Patient(request, authorityService.findByName("ROLE_PATIENT").get(0));
        patientService.save(patient);
    }

    public RegistrationRequest findById(Long id) {
        return registrationRequestRepository.findById(id).orElse(null);
    }
}
