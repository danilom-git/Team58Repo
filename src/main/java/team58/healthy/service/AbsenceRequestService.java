package team58.healthy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team58.healthy.dto.AbsenceRequestDTO;
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

    public List<AbsenceRequestDTO> getAllByClinic(Long id){
        List<AbsenceRequest> requests = absenceRequestRepository.findAllByClinicId(id);
        List<AbsenceRequestDTO> dtos = new ArrayList<AbsenceRequestDTO>();
        for(AbsenceRequest r : requests)
        {
            dtos.add(new AbsenceRequestDTO(r));
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
