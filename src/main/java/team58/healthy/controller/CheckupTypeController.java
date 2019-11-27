package team58.healthy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team58.healthy.dto.CheckupTypeDTO;
import team58.healthy.dto.ClinicDTO;
import team58.healthy.model.CheckupType;
import team58.healthy.model.Clinic;
import team58.healthy.service.CheckupTypeService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/checkupTypes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CheckupTypeController {

    @Autowired
    private CheckupTypeService checkupTypeService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<CheckupTypeDTO>> getAllClinics() {
        List<CheckupType> checkupTypes = checkupTypeService.findAll();
        List<CheckupTypeDTO> checkupTypeDTOS = new ArrayList<>();
        for (CheckupType checkupType : checkupTypes) {
            checkupTypeDTOS.add(new CheckupTypeDTO(checkupType));
        }

        return new ResponseEntity<>(checkupTypeDTOS, HttpStatus.OK);
    }
}
