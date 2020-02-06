package team58.healthy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team58.healthy.dto.CheckupRequestDTO;
import team58.healthy.dto.CheckupRequestViewDTO;
import team58.healthy.model.CheckupRequest;
import team58.healthy.repository.CheckupRequestRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CheckupRequestService {
    @Autowired
    private CheckupRequestRepository checkupRequestRepository;

    @Autowired
    private DoctorService doctorService;
    @Autowired
    private CheckupTypeService checkupTypeService;

    public List<CheckupRequest> findAll() {
        return checkupRequestRepository.findAll();
    }

    public CheckupRequest save(CheckupRequestDTO checkupRequestDTO) {
        CheckupRequest checkupRequest = new CheckupRequest();
        checkupRequest.setStartDate(checkupRequestDTO.getStartDate());
        checkupRequest.setEndDate(checkupRequestDTO.getEndDate());
        checkupRequest.setDoctor(doctorService.findById(checkupRequestDTO.getDoctorId()));
        checkupRequest.setCheckupType(checkupTypeService.findById(checkupRequestDTO.getCheckupTypeId()));

        return checkupRequestRepository.save(checkupRequest);
    }

    public List<CheckupRequestViewDTO> findByClinic(Long id) {
        List<CheckupRequest> req = checkupRequestRepository.findAllByClinicIdAndOnWait(id);
        List<CheckupRequestViewDTO> dtos = new ArrayList<CheckupRequestViewDTO>();
        for (CheckupRequest cr : req) {
            dtos.add(new CheckupRequestViewDTO(cr));
        }
        return dtos;
    }

    public void delete(CheckupRequest cr) {
        cr.setCheckupType(null);
        cr.setDoctor(null);
        cr.setClinic(null);
        cr.setPatient(null);
        checkupRequestRepository.delete(cr);
    }

    public void update(CheckupRequest checkupRequest)
    {
        checkupRequestRepository.save(checkupRequest);
    }

    public CheckupRequest findOne(Long id)
    {
        return  checkupRequestRepository.findById(id).orElseGet(null);
    }

    public CheckupRequestViewDTO getOne(Long id)
    {
        CheckupRequest cr = checkupRequestRepository.findById(id).orElseGet(null);
        if(cr != null)
        {
            CheckupRequestViewDTO dto = new CheckupRequestViewDTO(cr);
            return dto;
        }
        return null;
    }
}
