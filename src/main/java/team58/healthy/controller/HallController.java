package team58.healthy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import team58.healthy.dto.FirstDateAvailableDTO;
import team58.healthy.dto.HallDTO;

import team58.healthy.dto.HallSearchDTO;
import team58.healthy.model.Hall;
import team58.healthy.service.HallService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping(value = "api/halls")
@CrossOrigin
public class HallController {

    @Autowired
    private HallService hallService;

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole('DOCTOR') or hasRole('CLINIC_ADMIN')")
    public ResponseEntity<HallDTO> getHall(@PathVariable Long id)
    {
        HallDTO hallDTO = hallService.findOne(id);
        if(hallDTO != null)
            return new ResponseEntity<>(hallDTO,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @PutMapping(consumes = "application/json")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<HallDTO> updateHall(@RequestBody HallDTO hallDTO)
    {
        HallDTO ret = hallService.update(hallDTO);
        if(ret != null)
            return new ResponseEntity<>(ret,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/all")
    @PreAuthorize("hasRole('DOCTOR') or hasRole('CLINIC_ADMIN')")
    public ResponseEntity<List<HallDTO>> getAllHalls()
    {
        return new ResponseEntity<>(hallService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/all/clinic:{id}")
    @PreAuthorize("hasRole('DOCTOR') or hasRole('CLINIC_ADMIN')")
    public ResponseEntity<List<HallDTO>> getAllHallsByClinicId(@PathVariable Long id)
    {
        return new ResponseEntity<>(hallService.findAllByClinicId(id), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<HallDTO> saveHall(@RequestBody HallDTO hallDTO){
        hallDTO = hallService.save(hallDTO);
        return new ResponseEntity<>(hallDTO,HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<Void> deleteHall(@PathVariable Long id){
        if(hallService.remove(id))
        {
            return new ResponseEntity<>(HttpStatus.OK);
        }else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/name:{name}/number:{number}/date:{y}-{m}-{d}")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<List<HallSearchDTO>> searchHall(@PathVariable String name, @PathVariable String number, @PathVariable int y, @PathVariable int m, @PathVariable int d)
    {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(y, m, d);
        return new ResponseEntity<>(hallService.findOnDateAndNameOrNumber(cal.getTime(),name,number),HttpStatus.OK);
    }

    @GetMapping(value = "/firstAvailable/hall:{id}/request:{rid}")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<FirstDateAvailableDTO> getFirstDate(@PathVariable Long id, @PathVariable Long rid)
    {
        return new ResponseEntity<>(hallService.firstAvailable(id,rid),HttpStatus.OK);
    }

}
