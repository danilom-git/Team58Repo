package team58.healthy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team58.healthy.dto.CheckupDTOPretty;
import team58.healthy.dto.OneClickDTO;
import team58.healthy.dto.OneClickDTOPretty;
import team58.healthy.dto.OneClickViewDTO;
import team58.healthy.model.Checkup;
import team58.healthy.model.OneClick;
import team58.healthy.model.Patient;
import team58.healthy.repository.OneClickRepository;
import team58.healthy.security.TokenUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class OneClickService {
    @Autowired
    private OneClickRepository oneClickRepository;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private HallService hallService;
    @Autowired
    private CheckupTypeService checkupTypeService;
    @Autowired
    private ClinicService clinicService;
    @Autowired
    private CheckupService checkupService;
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private PatientService patientService;


    private Date ymdToDate(int y, int m, int d) {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(y, m - 1, d);

        return cal.getTime();
    }

    public List<OneClickViewDTO> findAll()
    {
        List<OneClick> oneClicks = oneClickRepository.findAll();
        List<OneClickViewDTO> dtos = new ArrayList<>();
        for(OneClick o : oneClicks)
        {
            dtos.add(new OneClickViewDTO(o));
        }
        return dtos;
    }

    public List<OneClickViewDTO> findAllByClinic(Long id)
    {
        List<OneClick> oneClicks = oneClickRepository.findAllByClinicId(id);
        List<OneClickViewDTO> dtos = new ArrayList<>();
        for(OneClick o : oneClicks)
        {
            dtos.add(new OneClickViewDTO(o));
        }
        return dtos;
    }

    public OneClickDTO save(OneClickDTO oneClickDTO){
        OneClick oc = new OneClick();

        List<Checkup> checkups = checkupService.findForOneClick(oneClickDTO.getStartTime(),oneClickDTO.getEndTime(),oneClickDTO.getDoctorId(),oneClickDTO.getHallId());

        if(checkups.isEmpty())
        {
            oc.setDoctor(doctorService.findOne2(oneClickDTO.getDoctorId()));
            oc.setHall(hallService.findOne2(oneClickDTO.getHallId()));
            oc.setCheckupType(checkupTypeService.findOne(oneClickDTO.getCheckupTypeId()));
            oc.setClinic(clinicService.findById(oneClickDTO.getClinicId()));
            oc.setDuration(oneClickDTO.getDuration());
            oc.setStartTime(oneClickDTO.getStartTime());
            oc.setEndTime(oneClickDTO.getEndTime());
            oc.setPrice(oneClickDTO.getPrice());
            return new OneClickDTO(oneClickRepository.save(oc));

        }else
        {
            return null;
        }
    }



    public List<OneClickDTOPretty> getPrettyAll() {
        List<OneClick> oneClicks = oneClickRepository.findAll();
        List<OneClickDTOPretty> dtos = new ArrayList<>();
        for (OneClick oneClick : oneClicks)
            dtos.add(new OneClickDTOPretty(oneClick));

        return dtos;
    }

    public List<OneClickDTOPretty> getPrettyByClinic(Long clinicId) {
        List<OneClick> oneClicks = oneClickRepository.findAllByClinicId(clinicId);
        List<OneClickDTOPretty> dtos = new ArrayList<>();
        for (OneClick oneClick : oneClicks)
            dtos.add(new OneClickDTOPretty(oneClick));

        return dtos;
    }

    public List<OneClickDTOPretty> getPrettyByCheckupType(Long checkupTypeId) {
        List<OneClick> oneClicks = oneClickRepository.getByCheckupType(checkupTypeId);
        List<OneClickDTOPretty> dtos = new ArrayList<>();
        for (OneClick oneClick : oneClicks)
            dtos.add(new OneClickDTOPretty(oneClick));

        return dtos;
    }

    public List<OneClickDTOPretty> getPrettyOnDate(int y, int m, int d) {
        List<OneClick> oneClicks = oneClickRepository.getOnDate(ymdToDate(y, m, d));
        List<OneClickDTOPretty> dtos = new ArrayList<>();
        for (OneClick oneClick : oneClicks)
            dtos.add(new OneClickDTOPretty(oneClick));

        return dtos;
    }

    public List<OneClickDTOPretty> getPrettyByCheckupTypeOnDate(Long checkupTypeId, int y, int m, int d) {
        List<OneClick> oneClicks = oneClickRepository.getByCheckupTypeOnDate(checkupTypeId, ymdToDate(y, m, d));
        List<OneClickDTOPretty> dtos = new ArrayList<>();
        for (OneClick oneClick : oneClicks)
            dtos.add(new OneClickDTOPretty(oneClick));

        return dtos;
    }

    public List<OneClickDTOPretty> getPrettyByClinicByCheckupType(Long clinicId, Long checkupTypeId) {
        List<OneClick> oneClicks = oneClickRepository.getByClinicByCheckupType(clinicId, checkupTypeId);
        List<OneClickDTOPretty> dtos = new ArrayList<>();
        for (OneClick oneClick : oneClicks)
            dtos.add(new OneClickDTOPretty(oneClick));

        return dtos;
    }

    public List<OneClickDTOPretty> getPrettyByClinicOnDate(Long clinicId, int y, int m, int d) {
        List<OneClick> oneClicks = oneClickRepository.getByClinicOnDate(clinicId, ymdToDate(y, m, d));
        List<OneClickDTOPretty> dtos = new ArrayList<>();
        for (OneClick oneClick : oneClicks)
            dtos.add(new OneClickDTOPretty(oneClick));

        return dtos;
    }

    public List<OneClickDTOPretty> getPrettyByClinicByCheckupTypeOnDate(Long clinicId, Long checkupTypeId, int y, int m, int d) {
        List<OneClick> oneClicks = oneClickRepository.getByClinicByCheckupTypeOnDate(clinicId, checkupTypeId, ymdToDate(y, m, d));
        List<OneClickDTOPretty> dtos = new ArrayList<>();
        for (OneClick oneClick : oneClicks)
            dtos.add(new OneClickDTOPretty(oneClick));

        return dtos;
    }

    public void delete(OneClick oneClick) {
        oneClick.setCheckupType(null);
        oneClick.setClinic(null);
        oneClick.setHall(null);
        oneClick.setDoctor(null);
        oneClickRepository.delete(oneClick);
    }

    public CheckupDTOPretty reserveOneClick(String token, Long oneClickId) {
        OneClick oneClick = oneClickRepository.findById(oneClickId).orElse(null);
        if (oneClick == null)
            return null;

        String email = tokenUtils.getUsernameFromToken(token.substring(7));
        Patient patient = patientService.findByEmail(email);

        Checkup checkup = new Checkup(oneClick, patient);

        this.delete(oneClick);
        return new CheckupDTOPretty(checkupService.save(checkup));
    }
}
