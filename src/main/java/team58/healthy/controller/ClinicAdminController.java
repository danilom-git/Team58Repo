package team58.healthy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import team58.healthy.dto.ClinicAdminDTO;
import team58.healthy.dto.DoctorDTO;
import team58.healthy.dto.TokenDTO;
import team58.healthy.repository.ClinicAdminRepository;
import team58.healthy.service.ClinicAdminService;

@RestController
@RequestMapping(value = "api/clinicAdmins")
@CrossOrigin
public class ClinicAdminController {

    @Autowired
    private ClinicAdminService clinicAdminService;

    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    @GetMapping(value = "/user")
    public ResponseEntity<ClinicAdminDTO> getFromToken(@RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(clinicAdminService.getFromToken(token), HttpStatus.OK);
    }

    @PostMapping(value = "/self")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<ClinicAdminDTO> getOne(@RequestBody TokenDTO tokenDTO){
        return new ResponseEntity<>(clinicAdminService.findOneByToken(tokenDTO.getToken()), HttpStatus.OK);
    }
}
