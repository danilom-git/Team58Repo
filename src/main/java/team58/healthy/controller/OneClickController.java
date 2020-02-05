package team58.healthy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import team58.healthy.dto.*;
import team58.healthy.service.OneClickService;

import java.util.List;


@RestController
@RequestMapping(value = "api/oneClickCheckup")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OneClickController {

    @Autowired
    private OneClickService oneClickService;

    @GetMapping(value = "/all")
    @PreAuthorize("hasRole('PATIENT') or hasRole('DOCTOR') or hasRole('CLINIC_ADMIN')")
    public ResponseEntity<List<OneClickViewDTO>> getAll()
    {
        return new ResponseEntity<>(oneClickService.findAll(),HttpStatus.OK);
    }

    @GetMapping(value = "/all/clinic:{id}")
    @PreAuthorize("hasRole('PATIENT') or hasRole('DOCTOR') or hasRole('CLINIC_ADMIN')")
    public ResponseEntity<List<OneClickViewDTO>> getAllbyClinic(@PathVariable Long id)
    {
        return new ResponseEntity<>(oneClickService.findAllByClinic(id),HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<OneClickDTO> postOneClick(@RequestBody OneClickDTO oneClickDTO)
    {
        OneClickDTO ret = oneClickService.save(oneClickDTO);
        if(ret != null)
            return new ResponseEntity<>(ret, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }



    @GetMapping(value = "/pretty")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<List<OneClickDTOPretty>> getPrettyAll() {
        return new ResponseEntity<>(oneClickService.getPrettyAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/pretty/clinic:{clinicId}")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<List<OneClickDTOPretty>> getPrettyByClinic(@PathVariable Long clinicId) {
        return new ResponseEntity<>(oneClickService.getPrettyByClinic(clinicId), HttpStatus.OK);
    }

    @GetMapping(value = "/pretty/checkupType:{checkupTypeId}")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<List<OneClickDTOPretty>> getPrettyByCheckupType(@PathVariable Long checkupTypeId) {
        return new ResponseEntity<>(oneClickService.getPrettyByCheckupType(checkupTypeId), HttpStatus.OK);
    }

    @GetMapping(value = "/pretty/date:{y}-{m}-{d}")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<List<OneClickDTOPretty>> getPrettyOnDate(@PathVariable int y, @PathVariable int m, @PathVariable int d) {
        return new ResponseEntity<>(oneClickService.getPrettyOnDate(y, m, d), HttpStatus.OK);
    }

    @GetMapping(value = "/pretty/checkupType:{checkupTypeId}/date:{y}-{m}-{d}")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<List<OneClickDTOPretty>> getPrettyByCheckupTypeOnDate(@PathVariable Long checkupTypeId,
        @PathVariable int y, @PathVariable int m, @PathVariable int d) {
        return new ResponseEntity<>(oneClickService.getPrettyByCheckupTypeOnDate(checkupTypeId, y, m, d), HttpStatus.OK);
    }

    @GetMapping(value = "/pretty/clinic:{clinicId}/checkupType:{checkupTypeId}")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<List<OneClickDTOPretty>> getPrettyByClinicByCheckupType(@PathVariable Long clinicId, @PathVariable Long checkupTypeId) {
        return new ResponseEntity<>(oneClickService.getPrettyByClinicByCheckupType(clinicId, checkupTypeId), HttpStatus.OK);
    }

    @GetMapping(value = "/pretty/clinic:{clinicId}/date:{y}-{m}-{d}")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<List<OneClickDTOPretty>> getPrettyByClinicOnDate(@PathVariable Long clinicId, @PathVariable int y, @PathVariable int m, @PathVariable int d) {
        return new ResponseEntity<>(oneClickService.getPrettyByClinicOnDate(clinicId, y, m, d), HttpStatus.OK);
    }

    @GetMapping(value = "/pretty/clinic:{clinicId}/checkupType:{checkupTypeId}/date:{y}-{m}-{d}")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<List<OneClickDTOPretty>> getPrettyByClinicByCheckupTypeOnDate(@PathVariable Long clinicId, @PathVariable Long checkupTypeId,
        @PathVariable int y, @PathVariable int m, @PathVariable int d) {
        return new ResponseEntity<>(oneClickService.getPrettyByClinicByCheckupTypeOnDate(clinicId, checkupTypeId, y, m, d), HttpStatus.OK);
    }

    @PutMapping(value = "/reserve:{oneClickId}")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<CheckupDTOPretty> reserveOneClick(@RequestHeader("Authorization") String token, @PathVariable Long oneClickId) {
        return new ResponseEntity<>(oneClickService.reserveOneClick(token, oneClickId), HttpStatus.OK);
    }
}
