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
@CrossOrigin
public class CheckupController {

    @Autowired
    private CheckupService checkupService;



    @GetMapping(value = "/all")
    @PreAuthorize("hasRole('DOCTOR') or hasRole('CLINIC_ADMIN')")
    public ResponseEntity<List<CheckupDTO>> getAllCheckups() {
        return new ResponseEntity<>(checkupService.getAllCheckups(), HttpStatus.OK);
    }

    @GetMapping(value = "/allOnDate/{y}-{m}-{d}")
    @PreAuthorize("hasRole('DOCTOR') or hasRole('CLINIC_ADMIN')")
    public ResponseEntity<List<CheckupDTO>> getAllCheckupsOnDate(@PathVariable int y, @PathVariable int m, @PathVariable int d) {
        return new ResponseEntity<>(checkupService.getAllCheckupsOnDate(y, m, d), HttpStatus.OK);
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

    @GetMapping(value = "/checkStart/patient:{pid}/doctor:{did}")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<CheckupDTO> checkStart(@PathVariable Long pid,@PathVariable Long did)
    {
        return new ResponseEntity<>(checkupService.findForCheckupStart(did,pid),HttpStatus.OK);
    }

    @PostMapping(value= "/request:{rid}", consumes = "application/json")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<Boolean> saveCheckup(@RequestBody CheckupDTO checkupDTO,@PathVariable Long rid) throws MessagingException {
        return new ResponseEntity<>(checkupService.sendNotif(checkupDTO,rid),HttpStatus.OK);
    }

    @GetMapping(value = "/checkMedicalRecord/patient:{pid}/doctor:{did}")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<List<CheckupDTO>> checkMediacalRecord(@PathVariable Long pid,@PathVariable Long did)
    {
        return new ResponseEntity<>(checkupService.checkMedicalRecord(did,pid),HttpStatus.OK);
    }
}
