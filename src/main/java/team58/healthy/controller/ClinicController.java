package team58.healthy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import team58.healthy.dto.ClinicDTO;
import team58.healthy.dto.ClinicReportDTO;
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
@CrossOrigin
public class ClinicController {

    @Autowired
    private ClinicService clinicService;
    @Autowired
    private ClinicCheckupTypeService clinicCheckupTypeService;

    @GetMapping(value = "/all")
    @PreAuthorize("hasRole('PATIENT') or hasRole('DOCTOR') or hasRole('CLINIC_ADMIN')")
    public ResponseEntity<List<ClinicDTO>> getAllClinics() {
        return new ResponseEntity<>(clinicService.getAllClinics(), HttpStatus.OK);
    }

    @GetMapping(value = "/report/clinic:{id}")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<ClinicReportDTO> getReport(@PathVariable Long id)
    {
        return new ResponseEntity<>(clinicService.getReport(id),HttpStatus.OK);
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
        return new ResponseEntity<>(clinicService.getAllClinicsWithCheckupType(checkupTypeId), HttpStatus.OK);
    }

    @PutMapping(value = "/save")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<ClinicDTO> save(@RequestBody ClinicDTO clinicDTO)
    {
        return new ResponseEntity<>(clinicService.saveInfo(clinicDTO),HttpStatus.OK);
    }


    @GetMapping(value = "/checkupType:{checkupTypeId}/date:{y}-{m}-{d}")
    @PreAuthorize("hasRole('PATIENT') or hasRole('DOCTOR') or hasRole('CLINIC_ADMIN')")
    public ResponseEntity<List<ClinicWithCheckupDTO>> getAllClinicsWithCheckupTypeOnDate(@PathVariable Long checkupTypeId,
        @PathVariable int y, @PathVariable int m, @PathVariable int d) {
        return new ResponseEntity<>(clinicService.getAllClinicsWithCheckupTypeOnDate(checkupTypeId, y, m, d), HttpStatus.OK);
    }
}
