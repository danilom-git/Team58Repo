package team58.healthy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
public class ClinicController {

    @Autowired
    private ClinicService clinicService;
    @Autowired
    private ClinicCheckupTypeService clinicCheckupTypeService;

    @GetMapping(value = "/all")
    @PreAuthorize("hasRole('PATIENT') or hasRole('DOCTOR') or hasRole('CLINIC_ADMIN')")
    public ResponseEntity<List<ClinicDTO>> getAllClinics() {
        List<Clinic> clinics = clinicService.findAll();
        List<ClinicDTO> clinicDTOs = new ArrayList<>();
        for (Clinic clinic : clinics) {
            clinicDTOs.add(new ClinicDTO(clinic));
        }

        return new ResponseEntity<>(clinicDTOs, HttpStatus.OK);
    }

    @GetMapping(value = "/getOne/clinic:{id}")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<ClinicDTO> getOne(@PathVariable Long id)
    {
        return new ResponseEntity<>(clinicService.findByIdDTO(id),HttpStatus.OK);
    }

    @GetMapping(value = "/checkupType:{checkupTypeId}")
    @PreAuthorize("hasRole('PATIENT') or hasRole('DOCTOR') or hasRole('CLINIC_ADMIN')")
    public ResponseEntity<List<ClinicWithCheckupDTO>> getAllClinicsWithCheckupType(@PathVariable Long checkupTypeId) {
        List<Clinic> clinics = clinicService.findAllWithCheckupType(checkupTypeId);

        List<ClinicWithCheckupDTO> clinicWithCheckupDTOS = new ArrayList<>();
        for (Clinic clinic : clinics) {
            ClinicCheckupType clinicCheckupType = clinicCheckupTypeService.findByClinicAndCheckupTypeId(clinic, checkupTypeId);
            clinicWithCheckupDTOS.add(new ClinicWithCheckupDTO(new ClinicDTO(clinic), clinicCheckupType));
        }

        return new ResponseEntity<>(clinicWithCheckupDTOS, HttpStatus.OK);
    }


    @GetMapping(value = "/checkupType:{checkupTypeId}/date:{y}-{m}-{d}")
    @PreAuthorize("hasRole('PATIENT') or hasRole('DOCTOR') or hasRole('CLINIC_ADMIN')")
    public ResponseEntity<List<ClinicWithCheckupDTO>> getAllClinicsWithCheckupTypeOnDate(@PathVariable Long checkupTypeId, @PathVariable int y, @PathVariable int m, @PathVariable int d) {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(y, m - 1, d);

        List<Clinic> clinics = clinicService.findAllWithCheckupTypeOnDate(checkupTypeId, cal.getTime());

        List<ClinicWithCheckupDTO> clinicWithCheckupDTOS = new ArrayList<>();
        for (Clinic clinic : clinics) {
            ClinicCheckupType clinicCheckupType = clinicCheckupTypeService.findByClinicAndCheckupTypeId(clinic, checkupTypeId);
            clinicWithCheckupDTOS.add(new ClinicWithCheckupDTO(new ClinicDTO(clinic), clinicCheckupType));
        }

        return new ResponseEntity<>(clinicWithCheckupDTOS, HttpStatus.OK);
    }
}
