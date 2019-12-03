package team58.healthy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team58.healthy.dto.ClinicDTO;
import team58.healthy.dto.ClinicWithCheckupDTO;
import team58.healthy.model.Clinic;
import team58.healthy.model.ClinicCheckupType;
import team58.healthy.service.CheckupService;
import team58.healthy.service.ClinicCheckupTypeService;
import team58.healthy.service.ClinicService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping(value = "api/clinics")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClinicController {

    @Autowired
    private ClinicService clinicService;
    @Autowired
    private ClinicCheckupTypeService clinicCheckupTypeService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<ClinicDTO>> getAllClinics() {
        List<Clinic> clinics = clinicService.findAll();
        List<ClinicDTO> clinicDTOs = new ArrayList<>();
        for (Clinic clinic : clinics) {
            clinicDTOs.add(new ClinicDTO(clinic));
        }

        return new ResponseEntity<>(clinicDTOs, HttpStatus.OK);
    }

    @GetMapping(value = "/checkupType:{id}")
    public ResponseEntity<List<ClinicDTO>> getAllClinicsWithCheckupType(@PathVariable Long id) {
        List<Clinic> clinics = clinicService.findAllWithCheckupType(id);
        List<ClinicDTO> clinicDTOs = new ArrayList<>();
        for (Clinic clinic : clinics) {
            clinicDTOs.add(new ClinicDTO(clinic));
        }

        return new ResponseEntity<>(clinicDTOs, HttpStatus.OK);
    }

    @GetMapping(value = "/checkupType:{id}/date:{y}-{m}-{d}")
    public ResponseEntity<List<ClinicWithCheckupDTO>> getAllClinicsWithCheckupType(@PathVariable Long id, @PathVariable int y, @PathVariable int m, @PathVariable int d) {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(y, m - 1, d);

        List<Clinic> clinics = clinicService.findAllWithCheckupTypeOnDate(id, cal.getTime());

        List<ClinicWithCheckupDTO> clinicWithCheckupDTOS = new ArrayList<>();
        for (Clinic clinic : clinics) {
            ClinicCheckupType clinicCheckupType = clinicCheckupTypeService.findByClinicAndCheckupTypeId(clinic, id);
            clinicWithCheckupDTOS.add(new ClinicWithCheckupDTO(new ClinicDTO(clinic), clinicCheckupType));
        }

        return new ResponseEntity<>(clinicWithCheckupDTOS, HttpStatus.OK);
    }
}
