package team58.healthy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import team58.healthy.dto.AbsenceRequestDTO;
import team58.healthy.model.AbsenceRequest;
import team58.healthy.service.AbsenceRequestService;

@RestController
@RequestMapping(value = "api/absenceRequests")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AbsenceRequestController {

    @Autowired
    AbsenceRequestService absenceRequestService;

    @PostMapping(consumes = "application/json")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<AbsenceRequestDTO> saveDoctorRequest(@RequestBody AbsenceRequestDTO absenceRequestDTO)
    {
        return new ResponseEntity<>(absenceRequestService.save(absenceRequestDTO), HttpStatus.OK);
    }
}
