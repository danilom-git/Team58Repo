package team58.healthy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team58.healthy.dto.CheckupRequestDTO;
import team58.healthy.model.CheckupRequest;
import team58.healthy.repository.CheckupRequestRepository;

import java.util.List;

@Service
public class CheckupRequestService {
    @Autowired
    private CheckupRequestRepository checkupRequestRepository;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private CheckupTypeService checkupTypeService;
    @Autowired
    private ClinicService clinicService;


    public List<CheckupRequest> findAll() { return checkupRequestRepository.findAll(); }

    public CheckupRequest save(CheckupRequestDTO checkupRequestDTO) {
        CheckupRequest checkupRequest = new CheckupRequest();
        checkupRequest.setStartDate(checkupRequestDTO.getStartDate());
        checkupRequest.setEndDate(checkupRequestDTO.getEndDate());
        checkupRequest.setClinic(clinicService.findById(checkupRequestDTO.getClinicId()));
        checkupRequest.setDoctor(doctorService.findById(checkupRequestDTO.getDoctorId()));
        checkupRequest.setCheckupType(checkupTypeService.findById(checkupRequestDTO.getCheckupTypeId()));

        return checkupRequestRepository.save(checkupRequest);
    }
}
