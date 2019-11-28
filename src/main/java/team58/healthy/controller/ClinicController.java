package team58.healthy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team58.healthy.dto.ClinicDTO;
import team58.healthy.model.Clinic;
import team58.healthy.service.CheckupService;
import team58.healthy.service.ClinicService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/clinics")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClinicController {

    @Autowired
    private ClinicService clinicService;
    @Autowired
    private CheckupService checkupService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<ClinicDTO>> getAllClinics() {
        List<Clinic> clinics = clinicService.findAll();
        List<ClinicDTO> clinicDTOs = new ArrayList<>();
        for (Clinic clinic : clinics) {
            clinicDTOs.add(new ClinicDTO(clinic));
        }

        return new ResponseEntity<>(clinicDTOs, HttpStatus.OK);
    }

    @GetMapping(value = "/allWithType/{id}")
    public ResponseEntity<List<ClinicDTO>> getAllClinicsWithCheckupType(@PathVariable Long id) {
        List<Clinic> clinics = clinicService.findAllWithCheckupType(id);
        List<ClinicDTO> clinicDTOs = new ArrayList<>();
        for (Clinic clinic : clinics) {
            clinicDTOs.add(new ClinicDTO(clinic));
        }

        return new ResponseEntity<>(clinicDTOs, HttpStatus.OK);
    }

    @GetMapping(value = "/allWithType/{id}/onDate/{y}-{m}-{d}")
    public ResponseEntity<List<ClinicDTO>> getAllClinicsWithCheckupType(@PathVariable Long id, @PathVariable int y, @PathVariable int m, @PathVariable int d) {
        List<Clinic> clinics = clinicService.findAllWithCheckupType(id);

        for (Clinic clinic : clinics) {

        }

        List<ClinicDTO> clinicDTOs = new ArrayList<>();
        for (Clinic clinic : clinics) {
            clinicDTOs.add(new ClinicDTO(clinic));
        }

        return new ResponseEntity<>(clinicDTOs, HttpStatus.OK);
    }
}
