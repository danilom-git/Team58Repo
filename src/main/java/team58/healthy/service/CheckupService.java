package team58.healthy.service;

import org.hibernate.annotations.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import team58.healthy.dto.CheckupDTO;
import team58.healthy.model.Checkup;
import team58.healthy.repository.CheckupRepository;

import java.util.Date;
import java.util.List;

@Service
public class CheckupService {

    @Autowired
    private CheckupRepository checkupRepository;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private HallService hallService;

    @Autowired
    private CheckupTypeService checkupTypeService;

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

    public List<Checkup> findAllByHallAndDate(Long id,Date date)
    {
        return checkupRepository.findAllByHallAndDate(id,date);
    }

    public List<Checkup> findForCheckupSchedule(Date startDate,Date endDate,Long hallId)
    {
        return checkupRepository.findAllForCheckupSchedule(startDate,endDate,hallId);
    }

    public CheckupDTO save(CheckupDTO checkupDTO)
    {
        List<Checkup> checkList = findForCheckupSchedule(checkupDTO.getStartDate(),checkupDTO.getEndDate(),checkupDTO.getHallId());
        if(checkList.isEmpty())
        {
            Checkup c = new Checkup();
            c.setStartDate(checkupDTO.getStartDate());
            c.setEndDate(checkupDTO.getEndDate());
            c.setDoctor(doctorService.findOne2(checkupDTO.getId()));
            c.setHall(hallService.findOne2(checkupDTO.getHallId()));
            c.setCheckupType(checkupTypeService.findOne(checkupDTO.getCheckupTypeId()));
            c.setPatient(patientService.getOne(checkupDTO.getPatientId()));
            return new CheckupDTO(checkupRepository.save(c));
        }
        return null;
    }
}
