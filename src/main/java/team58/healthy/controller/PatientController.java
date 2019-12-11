package team58.healthy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team58.healthy.dto.PatientDTO;
import team58.healthy.service.PatientService;


import java.util.List;

@RestController
@RequestMapping(value = "api/patients")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<PatientDTO>> getAll()
    {
        return new ResponseEntity<>(patientService.getAll(), HttpStatus.OK);
    }


}
