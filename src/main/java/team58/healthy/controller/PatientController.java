package team58.healthy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(value = "/id:{id}")
    public ResponseEntity<PatientDTO> getOne(@PathVariable Long id)
    {
        return new ResponseEntity<>(patientService.getOneById(id),HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<PatientDTO>> getAll()
    {
        return new ResponseEntity<>(patientService.getAll(), HttpStatus.OK);
    }


}
