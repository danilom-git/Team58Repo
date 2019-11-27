package team58.healthy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team58.healthy.dto.DoctorDTO;
import team58.healthy.model.Doctor;
import team58.healthy.service.DoctorService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/doctors")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<DoctorDTO>> getAllDoctors()
    {
        List<Doctor> doctors = doctorService.findAll();

        List<DoctorDTO> doctorsDTO = new ArrayList<DoctorDTO>();
        for(Doctor d : doctors)
        {
            doctorsDTO.add(new DoctorDTO(d));
        }
        return new ResponseEntity<>(doctorsDTO, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<DoctorDTO> saveDoctor(@RequestBody DoctorDTO doctorDTO){
        Doctor doctor = new Doctor();
        doctor.setName(doctorDTO.getName());
        doctor.setLastName(doctorDTO.getLastName());

        doctor = doctorService.save(doctor);
        return new ResponseEntity<>(new DoctorDTO(doctor),HttpStatus.CREATED);
    }
}
