package team58.healthy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import team58.healthy.dto.PatientDTO;
import team58.healthy.service.PatientService;


import java.util.List;

@RestController
@RequestMapping(value = "api/patients")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PreAuthorize("hasRole('CLINIC_ADMIN') or hasRole('DOCTOR')")
    @GetMapping(value = "/name:{name},lastName:{lastName},healthInsuranceId:{healthInsuranceId}")
    public ResponseEntity<List<PatientDTO>> search(@PathVariable String name,@PathVariable String lastName,@PathVariable String healthInsuranceId)
    {
        return new ResponseEntity<>(patientService.search(name,lastName,healthInsuranceId),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('CLINIC_ADMIN') or hasRole('DOCTOR')")
    @GetMapping(value = "/id:{id}")
    public ResponseEntity<PatientDTO> getOne(@PathVariable Long id)
    {
        return new ResponseEntity<>(patientService.getOneById(id),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('CLINIC_ADMIN') or hasRole('DOCTOR')")
    @GetMapping(value = "/all")
    public ResponseEntity<List<PatientDTO>> getAll()
    {
        return new ResponseEntity<>(patientService.getAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('PATIENT')")
    @GetMapping(value = "/user")
    public ResponseEntity<PatientDTO> getFromToken(@RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(patientService.getFromToken(token), HttpStatus.OK);
    }
}
