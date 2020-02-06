package team58.healthy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import team58.healthy.dto.CheckupDTO;
import team58.healthy.dto.CheckupDTOPretty;
import team58.healthy.dto.FirstDateAvailableDTO;
import team58.healthy.model.Checkup;
import team58.healthy.service.CheckupService;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "api/checkups")
public class CheckupController {

    @Autowired
    private CheckupService checkupService;



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

    @GetMapping(value = "/user")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<List<CheckupDTOPretty>> getFromUser(@RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(checkupService.getFromUser(token), HttpStatus.OK);
    }

    @GetMapping(value = "/user/type:{typeId}")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<List<CheckupDTOPretty>> getFromPatient(@RequestHeader("Authorization") String token, @PathVariable Long typeId) {
        return new ResponseEntity<>(checkupService.getFromUserByType(token, typeId), HttpStatus.OK);
    }

    @GetMapping(value = "/confirm/token:{token}/request:{id}/hall:{hid}")
    public ResponseEntity<CheckupDTO> save(@PathVariable String token,@PathVariable Long id,@PathVariable Long hid)
    {
        return new ResponseEntity<>(checkupService.saveCheckup(token,id,hid),HttpStatus.OK);
    }

    @GetMapping(value = "/cancel/token:{token}/request:{id}/hall:{hid}")
    public ResponseEntity<String> delete(@PathVariable String token,@PathVariable Long id,@PathVariable Long hid)
    {
        return new ResponseEntity<>(checkupService.delete(token,id),HttpStatus.OK);
    }

    @PostMapping(value= "/request:{rid}", consumes = "application/json")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<Boolean> saveCheckup(@RequestBody CheckupDTO checkupDTO,@PathVariable Long rid) throws MessagingException {
        return new ResponseEntity<>(checkupService.sendNotif(checkupDTO,rid),HttpStatus.OK);
    }

}
