package team58.healthy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import team58.healthy.dto.PatientDTO;
import team58.healthy.dto.RegistrationRequestDTO;
import team58.healthy.model.RegistrationRequest;
import team58.healthy.service.RegistrationRequestService;

@RestController
@RequestMapping(value = "api/registration")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RegistrationRequestController {

    @Autowired
    private RegistrationRequestService registrationRequestService;

    @PostMapping(consumes = "application/json", value = "/new")
    public ResponseEntity<RegistrationRequestDTO> saveRequest(@RequestBody RegistrationRequestDTO requestDTO) {
        RegistrationRequest request = registrationRequestService.save(requestDTO);
        return new ResponseEntity<>(new RegistrationRequestDTO(request), HttpStatus.CREATED);
    }

    @PutMapping(value = "/enable/id:{requestId}")
    @PreAuthorize("hasRole('ROLE_CLINIC_CENTER_ADMIN')")
    public ResponseEntity<String> enableAccount(@PathVariable Long requestId) {
         return new ResponseEntity<>(registrationRequestService.enableAccount(requestId), HttpStatus.OK);
    }

    @GetMapping(value = "/verify/user:{token}/request:{requestId}")
    public ResponseEntity<String> verifyAccount(@PathVariable Long requestId, @PathVariable String token) {
        return new ResponseEntity<>(registrationRequestService.verifyAccount(requestId, token), HttpStatus.OK);
    }
}
