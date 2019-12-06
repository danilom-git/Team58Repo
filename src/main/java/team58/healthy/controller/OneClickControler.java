package team58.healthy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team58.healthy.dto.OneClickDTO;
import team58.healthy.model.OneClick;
import team58.healthy.service.OneClickService;

import javax.validation.constraints.PastOrPresent;


@RestController
@RequestMapping(value = "api/oneClickCheckup")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OneClickControler {

    @Autowired
    private OneClickService oneClickService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<OneClickDTO> postOneClick(@RequestBody OneClickDTO oneClickDTO)
    {
        OneClickDTO ret = oneClickService.save(oneClickDTO);
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

}
