package team58.healthy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import team58.healthy.dto.HallDTO;

import team58.healthy.model.Hall;
import team58.healthy.service.HallService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/halls")
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

    @PostMapping(consumes = "application/json")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<HallDTO> saveHall(@RequestBody HallDTO hallDTO){
        hallDTO = hallService.save(hallDTO);
        return new ResponseEntity<>(hallDTO,HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<Void> deleteHall(@PathVariable Long id){
        System.out.println("ID ZA BRSIANJE" + id.toString());

        if(hallService.remove(id))
        {
            return new ResponseEntity<>(HttpStatus.OK);
        }else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
