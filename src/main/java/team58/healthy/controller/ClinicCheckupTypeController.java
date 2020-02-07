package team58.healthy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import team58.healthy.dto.ClinicCheckupTypeDTO;
import team58.healthy.service.ClinicCheckupTypeService;

@RestController
@RequestMapping(value = "api/clinicCheckupTypes")
@CrossOrigin
public class ClinicCheckupTypeController {

    @Autowired
    private ClinicCheckupTypeService clinicCheckupTypeService;

    @PostMapping(value = "/clinic:{id}", consumes = "application/json")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<ClinicCheckupTypeDTO> save(@RequestBody ClinicCheckupTypeDTO clinicCheckupTypeDTO, @PathVariable Long id) {
        return new ResponseEntity<>(clinicCheckupTypeService.save(clinicCheckupTypeDTO, id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/clinic:{cid}/type:{id}")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<Boolean> delete(@PathVariable Long cid, @PathVariable Long id) {
        return new ResponseEntity<>(clinicCheckupTypeService.delete(id,cid),HttpStatus.OK);
    }
}