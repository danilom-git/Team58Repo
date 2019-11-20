package team58.healthy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team58.healthy.dto.ClinicDTO;
import team58.healthy.model.Clinic;
import team58.healthy.service.ClinicService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/clinics")
public class ClinicController {

    @Autowired
    private ClinicService clinicService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<ClinicDTO>> getAllClinics() {
        List<Clinic> clinics = clinicService.findAll();
        List<ClinicDTO> clinicDTOs = new ArrayList<>();
        for (Clinic clinic : clinics) {
            clinicDTOs.add(new ClinicDTO(clinic));
        }

        return new ResponseEntity<>(clinicDTOs, HttpStatus.OK);
    }
}
