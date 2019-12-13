package team58.healthy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team58.healthy.dto.ClinicAdminDTO;
import team58.healthy.repository.ClinicAdminRepository;
import team58.healthy.service.ClinicAdminService;

@RestController
@RequestMapping(value = "api/clinicAdmins")
@CrossOrigin
public class ClinicAdminController {

    @Autowired
    private ClinicAdminService clinicAdminService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClinicAdminDTO> getOne(@PathVariable Long id){
        return new ResponseEntity<>(clinicAdminService.findOne(id), HttpStatus.OK);
    }
}
