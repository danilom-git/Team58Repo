package team58.healthy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
public class CheckupController {

    @Autowired
    private CheckupService checkupService;

    /*@GetMapping(value = "/{y1}:{m1}:{d1}:{h1}:{s1},{y2}:{m2}:{d2}:{h2}:{s2},{id1},{id2}")
    public ResponseEntity<List<CheckupDTO>> getShit(@PathVariable int y1, @PathVariable int m1, @PathVariable int d1,@PathVariable int h1,@PathVariable int s1,@PathVariable int y2, @PathVariable int m2, @PathVariable int d2,@PathVariable int h2,@PathVariable int s2,@PathVariable Long id1,@PathVariable Long id2){
        Calendar c = Calendar.getInstance();
        c.set(y1,m1-1,d1,h1,s1);
        Date start = c.getTime();
        c.clear();
        c.set(y2,m2-1,d2,h2,s2);
        Date end = c.getTime();

        List<Checkup> checkups = checkupService.findForOneClick(start,end,id1,id2);
        List<CheckupDTO> checkupDTOS = new ArrayList<>();
        for (Checkup checkup : checkups) {
            checkupDTOS.add(new CheckupDTO(checkup));
        }

        return new ResponseEntity<>(checkupDTOS,HttpStatus.OK);
    }*/

    @GetMapping(value = "/all")
    @PreAuthorize("hasRole('DOCTOR') or hasRole('CLINIC_ADMIN')")
    public ResponseEntity<List<CheckupDTO>> getAllCheckups() {
        List<Checkup> checkups = checkupService.findAll();
        List<CheckupDTO> checkupDTOS = new ArrayList<>();
        for (Checkup checkup : checkups) {
            checkupDTOS.add(new CheckupDTO(checkup));
        }

        return new ResponseEntity<>(checkupDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/allOnDate/{y}-{m}-{d}")
    @PreAuthorize("hasRole('DOCTOR') or hasRole('CLINIC_ADMIN')")
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
