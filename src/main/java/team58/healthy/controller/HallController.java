package team58.healthy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import team58.healthy.dto.HallDTO;

import team58.healthy.model.Hall;
import team58.healthy.service.HallService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/halls")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HallController {

    @Autowired
    private HallService hallService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<HallDTO>> getAllHalls()
    {
        List<Hall> halls = hallService.findAll();

        List<HallDTO> hallsDTO = new ArrayList<HallDTO>();
        for(Hall h : halls)
        {
            hallsDTO.add(new HallDTO(h));
        }
        return new ResponseEntity<>(hallsDTO, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<HallDTO> saveHall(@RequestBody HallDTO hallDTO){
        Hall hall = new Hall();
        hall.setName(hallDTO.getName());

        System.out.println(hall.getName());

        hall = hallService.save(hall);
        return new ResponseEntity<>(new HallDTO(hall),HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteHall(@PathVariable Long id){
        System.out.println("ID ZA BRSIANJE" + id.toString());
        Hall hall = hallService.findOne(id);
        System.out.println(hall.toString());
        if(hall != null && (hall.getClinic() == null))
        {
            hallService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
