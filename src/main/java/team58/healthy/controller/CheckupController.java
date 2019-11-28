package team58.healthy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team58.healthy.dto.CheckupDTO;
import team58.healthy.model.Checkup;
import team58.healthy.service.CheckupService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "api/checkups")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CheckupController {

    @Autowired
    private CheckupService checkupService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<CheckupDTO>> getAllCheckups() {
        List<Checkup> checkups = checkupService.findAll();
        List<CheckupDTO> checkupDTOS = new ArrayList<>();
        for (Checkup checkup : checkups) {
            checkupDTOS.add(new CheckupDTO(checkup));
        }

        return new ResponseEntity<>(checkupDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/allOnDate/{y}-{m}-{d}")
    public ResponseEntity<List<CheckupDTO>> getAllCheckupsOnDate(@PathVariable int y, @PathVariable int m, @PathVariable int d) {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(y, m - 1, d);
        List<Checkup> checkups = checkupService.findAllOnDate(cal.getTime());
        List<CheckupDTO> checkupDTOS = new ArrayList<>();
        for (Checkup checkup : checkups) {
            checkupDTOS.add(new CheckupDTO(checkup));
        }

        return new ResponseEntity<>(checkupDTOS, HttpStatus.OK);
    }
}
