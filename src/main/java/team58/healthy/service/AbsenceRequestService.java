package team58.healthy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team58.healthy.dto.AbsenceRequestDTO;
import team58.healthy.dto.AbsenceRequestViewDTO;
import team58.healthy.model.AbsenceRequest;
import team58.healthy.model.Doctor;
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

    @Autowired
    private EmailService emailService;

    public List<AbsenceRequestViewDTO> getAllByClinic(Long id){
        List<AbsenceRequest> requests = absenceRequestRepository.findAllByClinicIdAndAnswered(id,false);
        List<AbsenceRequestViewDTO> dtos = new ArrayList<AbsenceRequestViewDTO>();
        for(AbsenceRequest r : requests)
        {
            dtos.add(new AbsenceRequestViewDTO(r));
        }
        return dtos;
    }

    public void delete(Long id,String reason,Long uid){
        AbsenceRequest req = absenceRequestRepository.findById(id).orElseGet(null);
        Doctor doc = doctorService.findById(uid);
        if(req != null) {
            emailService.sendSimpleMail(doc.getEmail(),"Absence request","You're request for absence has been denied becouse of " + reason+  ".");
            req.setDoctor(null);
            req.setClinic(null);
            absenceRequestRepository.delete(req);
        }
    }

    public Boolean changeAnswered(Long id,Long uid)
    {
        AbsenceRequest req = absenceRequestRepository.findById(id).orElseGet(null);
        Doctor doc = doctorService.findById(uid);
        if(req != null && doc != null) {
            emailService.sendSimpleMail(doc.getEmail(),"Absence request","You're request for absence has been accepted.");
            req.setAnswered(true);
            req = absenceRequestRepository.save(req);
            return true;
        }else
            return false;
    }

    public AbsenceRequestDTO save(AbsenceRequestDTO requestDTO)
    {
        List<AbsenceRequest> reqs = absenceRequestRepository.findAllByDoctorId(requestDTO.getDoctorId());

        if(reqs.isEmpty()) {
            AbsenceRequest req = new AbsenceRequest();
            req.setClinic(clinicService.findById(requestDTO.getClinicId()));
            req.setDoctor(doctorService.findOne2(requestDTO.getDoctorId()));
            req.setStartDate(requestDTO.getStartDate());
            req.setEndDate(requestDTO.getEndDate());
            req.setAnswered(requestDTO.getAnswered());
            req.setType(requestDTO.getType());

            return new AbsenceRequestDTO(absenceRequestRepository.save(req));
        }else
            return new AbsenceRequestDTO(new AbsenceRequest());

    }
}
