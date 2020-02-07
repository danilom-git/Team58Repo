package team58.healthy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import team58.healthy.dto.CheckupTypeDTO;
import team58.healthy.dto.ClinicCheckupTypeDTO;
import team58.healthy.model.CheckupType;
import team58.healthy.service.CheckupTypeService;
import team58.healthy.service.ClinicCheckupTypeService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/checkupTypes")
public class CheckupTypeController {

    @Autowired
    private CheckupTypeService checkupTypeService;

    @GetMapping(value = "/all")
    @PreAuthorize("hasRole('PATIENT') or hasRole('DOCTOR') or hasRole('CLINIC_ADMIN')")
    public ResponseEntity<List<CheckupTypeDTO>> getAllCheckupTypes() {
        List<CheckupType> checkupTypes = checkupTypeService.findAll();
        List<CheckupTypeDTO> checkupTypeDTOS = new ArrayList<>();
        for (CheckupType checkupType : checkupTypes) {
            checkupTypeDTOS.add(new CheckupTypeDTO(checkupType));
        }

        return new ResponseEntity<>(checkupTypeDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/all/clinic:{id}")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<List<ClinicCheckupTypeDTO>> getAllByClinic(@PathVariable Long id)
    {
        return new ResponseEntity<>(checkupTypeService.findByClinic(id),HttpStatus.OK);
    }

    @GetMapping(value = "/allFalse/clinic:{id}")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<List<CheckupTypeDTO>> getAllByClinicFalse(@PathVariable Long id)
    {
        return new ResponseEntity<>(checkupTypeService.findByClinicFalse(id),HttpStatus.OK);
    }
}
