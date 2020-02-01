package team58.healthy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import team58.healthy.dto.ClinicDTO;
import team58.healthy.dto.DoctorDTO;
import team58.healthy.dto.DoctorWithAvailableTimeDTO;
import team58.healthy.dto.TokenDTO;
import team58.healthy.model.Doctor;
import team58.healthy.service.DoctorService;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping(value = "api/doctors")
@CrossOrigin
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole('PATIENT') or hasRole('DOCTOR') or hasRole('CLINIC_ADMIN')")
    public ResponseEntity<DoctorDTO> getDoctor(@PathVariable Long id){
        DoctorDTO ret = doctorService.findOne(id);
        return new ResponseEntity<>(ret,HttpStatus.OK);
    }

    @PostMapping(value = "/self")
    @PreAuthorize("hasRole('CLINIC_ADMIN') or hasRole('DOCTOR')")
    public ResponseEntity<DoctorDTO> getSelf(@RequestBody TokenDTO token){
        DoctorDTO ret = doctorService.findByToken(token.getToken());
        return new ResponseEntity<>(ret,HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    @PreAuthorize("hasRole('PATIENT') or hasRole('DOCTOR') or hasRole('CLINIC_ADMIN')")
    public ResponseEntity<List<DoctorDTO>> getAllDoctors()
    {
        return new ResponseEntity<>(doctorService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/all/clinic:{id}")
    public ResponseEntity<List<DoctorDTO>> getAllDoctorsByClinicId(@PathVariable Long id)
    {
        return new ResponseEntity<>(doctorService.findAllByClinicDTO(id), HttpStatus.OK);
    }

    @PutMapping(consumes = "application/json")
    @PreAuthorize("hasRole('DOCTOR') or hasRole('CLINIC_ADMIN')")
    public ResponseEntity<DoctorDTO> updateDoctor(@RequestBody DoctorDTO doctorDTO)
    {
        DoctorDTO ret = doctorService.update(doctorDTO);
        if(ret != null)
            return new ResponseEntity<>(ret,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping(consumes = "application/json")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<DoctorDTO> saveDoctor(@RequestBody DoctorDTO doctorDTO){
        Doctor doctor = doctorService.save(doctorDTO);
        return new ResponseEntity<>(new DoctorDTO(doctor),HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id){
            if(doctorService.remove(id))
                return new ResponseEntity<>(HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/clinic:{clinicId}")
    @PreAuthorize("hasRole('PATIENT') or hasRole('DOCTOR') or hasRole('CLINIC_ADMIN')")
    public ResponseEntity<List<DoctorDTO>> getAllInClinicWithCheckupType(@PathVariable Long clinicId) {
        List<Doctor> doctors = doctorService.findAllByClinic(clinicId);

        List<DoctorDTO> doctorDTOS = new ArrayList<>();
        for (Doctor doctor : doctors)
            doctorDTOS.add(new DoctorDTO(doctor));
        return new ResponseEntity<>(doctorDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/clinic:{clinicId}/checkupType:{checkupTypeId}")
    @PreAuthorize("hasRole('PATIENT') or hasRole('DOCTOR') or hasRole('CLINIC_ADMIN')")
    public ResponseEntity<List<DoctorDTO>> getAllInClinicWithCheckupType(
            @PathVariable Long clinicId, @PathVariable Long checkupTypeId) {
        List<Doctor> doctors = doctorService.findAllByClinicAndCheckupType(clinicId, checkupTypeId);

        List<DoctorDTO> doctorDTOS = new ArrayList<>();
        for (Doctor doctor : doctors)
            doctorDTOS.add(new DoctorDTO(doctor));
        return new ResponseEntity<>(doctorDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/clinic:{clinicId}/checkupType:{checkupTypeId}/date:{y}-{m}-{d}")
    @PreAuthorize("hasRole('PATIENT') or hasRole('DOCTOR') or hasRole('CLINIC_ADMIN')")
    public ResponseEntity<List<DoctorWithAvailableTimeDTO>> getAllInClinicWithCheckupTypeOnDate(
            @PathVariable Long clinicId, @PathVariable Long checkupTypeId, @PathVariable int y, @PathVariable int m, @PathVariable int d) {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(y, m - 1, d);
        List<Doctor> doctors = doctorService.findAllByClinicWithCheckupTypeOnDate(clinicId, checkupTypeId, cal.getTime());

        List<DoctorWithAvailableTimeDTO> doctorDTOS = new ArrayList<>();
        for (Doctor doctor : doctors)
            doctorDTOS.add(new DoctorWithAvailableTimeDTO(new DoctorDTO(doctor), doctorService.availableTimeOnDate(doctor, cal.getTime())));
        return new ResponseEntity<>(doctorDTOS, HttpStatus.OK);
    }
}
