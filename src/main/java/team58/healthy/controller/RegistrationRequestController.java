package team58.healthy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import team58.healthy.dto.RegistrationRequestDTO;
import team58.healthy.model.RegistrationRequest;
import team58.healthy.service.RegistrationRequestService;

@RestController
@RequestMapping(value = "api/registration")
public class RegistrationRequestController {

    @Autowired
    private RegistrationRequestService registrationRequestService;

    @PostMapping(consumes = "application/json", value = "/new")
    public ResponseEntity<RegistrationRequestDTO> saveRequest(@RequestBody RegistrationRequestDTO requestDTO) {
        RegistrationRequest request = registrationRequestService.save(requestDTO);
        return new ResponseEntity<>(new RegistrationRequestDTO(request), HttpStatus.CREATED);
    }

    @PutMapping(value = "/enable/id:{id}")
    @PreAuthorize("hasRole('ROLE_CLINIC_CENTER_ADMIN')")
    public ResponseEntity<String> enableAccount(@PathVariable Long id) {
        //System.out.println("Registration Request ID: " + id);
        RegistrationRequest request = registrationRequestService.findById(id);
        if (request == null)
            return new ResponseEntity<>("A request with the selected ID does not exist.", HttpStatus.BAD_REQUEST);
        if (request.isArchived())
            return new ResponseEntity<>("This registration request is archived and can not be altered.", HttpStatus.BAD_REQUEST);
        if (request.isAccepted())
            return new ResponseEntity<>("This registration request is already accepted.", HttpStatus.BAD_REQUEST);

        registrationRequestService.enableAccount(request);
        return new ResponseEntity<>("Created new patient account.", HttpStatus.OK);
    }
}
