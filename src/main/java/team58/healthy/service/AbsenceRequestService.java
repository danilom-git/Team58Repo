package team58.healthy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team58.healthy.dto.AbsenceRequestDTO;
import team58.healthy.dto.AbsenceRequestViewDTO;
import team58.healthy.model.AbsenceRequest;
import team58.healthy.repository.AbsenceRequestRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AbsenceRequestService {

    @Autowired
    private AbsenceRequestRepository absenceRequestRepository;

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private DoctorService doctorService;

    public List<AbsenceRequestViewDTO> getAllByClinic(Long id){
        List<AbsenceRequest> requests = absenceRequestRepository.findAllByClinicIdAndAnswered(id,false);
        List<AbsenceRequestViewDTO> dtos = new ArrayList<AbsenceRequestViewDTO>();
        for(AbsenceRequest r : requests)
        {
            dtos.add(new AbsenceRequestViewDTO(r));
        }
        return dtos;
    }

    public AbsenceRequestDTO save(AbsenceRequestDTO requestDTO)
    {
        AbsenceRequest req = new AbsenceRequest();
        req.setClinic(clinicService.findById(requestDTO.getClinicId()));
        req.setDoctor(doctorService.findOne2(requestDTO.getDoctorId()));
        req.setStartDate(requestDTO.getStartDate());
        req.setEndDate(requestDTO.getEndDate());
        req.setAnswered(requestDTO.getAnswered());
        req.setType(requestDTO.getType());

        return new AbsenceRequestDTO(absenceRequestRepository.save(req));
    }
}
