package team58.healthy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import team58.healthy.dto.CheckupRequestDTO;
import team58.healthy.dto.CheckupRequestViewDTO;
import team58.healthy.dto.FirstDateAvailableDTO;
import team58.healthy.model.CheckupRequest;
import team58.healthy.service.CheckupRequestService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/checkupRequests")
public class CheckupRequestController {

    @Autowired
    private CheckupRequestService checkupRequestService;

    @GetMapping(value = "/all")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<List<CheckupRequestDTO>> getAll() {
        List<CheckupRequest> checkupRequests = checkupRequestService.findAll();
        List<CheckupRequestDTO> checkupRequestDTOS = new ArrayList<>();
        for (CheckupRequest checkupRequest : checkupRequests)
            checkupRequestDTOS.add(new CheckupRequestDTO(checkupRequest));

        return new ResponseEntity<>(checkupRequestDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/checkupRequest:{id}")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<CheckupRequestViewDTO> getOne(@PathVariable Long id)
    {
        return new ResponseEntity<>(checkupRequestService.getOne(id),HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    @PreAuthorize("hasRole('PATIENT') or hasRole('DOCTOR') or hasRole('CLINIC_ADMIN')")
    public ResponseEntity<CheckupRequestDTO> post(@RequestBody CheckupRequestDTO checkupRequestDTO) {
        return new ResponseEntity<>(new CheckupRequestDTO(checkupRequestService.save(checkupRequestDTO)), HttpStatus.OK);
    }

    @PostMapping(value =  "/changeDate/request:{id}",consumes = "application/json")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<CheckupRequestDTO> changeDate(@RequestBody FirstDateAvailableDTO firstDateAvailableDTO,@PathVariable Long id) {
        return new ResponseEntity<>(checkupRequestService.changeDate(firstDateAvailableDTO,id), HttpStatus.OK);
    }

    @GetMapping(value = "/all/clinic:{id}")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<List<CheckupRequestViewDTO>> getAllFromClinic(@PathVariable Long id)
    {
        return new ResponseEntity<>(checkupRequestService.findByClinic(id),HttpStatus.OK);
    }
}
