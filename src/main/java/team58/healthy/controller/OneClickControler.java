package team58.healthy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import team58.healthy.dto.OneClickDTO;
import team58.healthy.dto.OneClickViewDTO;
import team58.healthy.model.OneClick;
import team58.healthy.service.OneClickService;

import javax.validation.constraints.PastOrPresent;
import javax.websocket.server.PathParam;
import java.util.List;


@RestController
@RequestMapping(value = "api/oneClickCheckup")
public class OneClickControler {

    @Autowired
    private OneClickService oneClickService;

    @GetMapping(value = "/all")
    @PreAuthorize("hasRole('PATIENT') or hasRole('DOCTOR') or hasRole('CLINIC_ADMIN')")
    public ResponseEntity<List<OneClickViewDTO>> getAll()
    {
        return new ResponseEntity<>(oneClickService.findAll(),HttpStatus.OK);
    }

    @GetMapping(value = "/all/clinic:{id}")
    @PreAuthorize("hasRole('PATIENT') or hasRole('DOCTOR') or hasRole('CLINIC_ADMIN')")
    public ResponseEntity<List<OneClickViewDTO>> getAllbyClinic(@PathVariable Long id)
    {
        return new ResponseEntity<>(oneClickService.findAllByClinic(id),HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<OneClickDTO> postOneClick(@RequestBody OneClickDTO oneClickDTO)
    {
        OneClickDTO ret = oneClickService.save(oneClickDTO);
        if(ret != null)
            return new ResponseEntity<>(ret, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
