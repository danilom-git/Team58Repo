package team58.healthy.service;

import org.hibernate.annotations.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team58.healthy.dto.CheckupDTO;
import team58.healthy.dto.CheckupDTOPretty;
import team58.healthy.model.Checkup;
import team58.healthy.model.CheckupRequest;
import team58.healthy.model.Patient;
import team58.healthy.repository.CheckupRepository;
import team58.healthy.security.TokenUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CheckupService {

    @Autowired
    private CheckupRepository checkupRepository;
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private PatientService patientService;

    public List<Checkup> findAll() { return checkupRepository.findAll(); }

    public List<Checkup> findAllOnDate(Date date) {
        return checkupRepository.findAllOnDate(date);
    }

    public List<Checkup> findAllOnDateByDoctor(Date date, Long doctorId) {
        return checkupRepository.findAllOnDateByDoctor(date, doctorId);
    }

    public List<Checkup> findAllByHall(Long id){return checkupRepository.findAllByHall(id);}

    public List<Checkup> findForOneClick (Date start,Date end,Long idd,Long idh)
    {
        return checkupRepository.findAllByDateAndDoctor(start,end,idd,idh);
    }

    public List<CheckupDTOPretty> getFromUser(String token) {
        String email = tokenUtils.getUsernameFromToken(token.substring(7));
        Patient patient = patientService.findByEmail(email);

        List<CheckupDTOPretty> dtos = new ArrayList<>();
        List<Checkup> checkups = checkupRepository.findByPatientId(patient.getId());
        for (Checkup checkup : checkups)
            dtos.add(new CheckupDTOPretty(checkup));

        return dtos;
    }

    public List<CheckupDTOPretty> getFromUserByType(String token, Long checkupTypeId) {
        String email = tokenUtils.getUsernameFromToken(token.substring(7));
        Patient patient = patientService.findByEmail(email);

        List<CheckupDTOPretty> dtos = new ArrayList<>();
        List<Checkup> checkups = checkupRepository.findByPatientIdAndTypeId(patient.getId(), checkupTypeId);
        for (Checkup checkup : checkups)
            dtos.add(new CheckupDTOPretty(checkup));

        return dtos;
    }
}
