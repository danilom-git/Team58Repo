package team58.healthy.service;

import org.hibernate.annotations.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import team58.healthy.dto.CheckupDTO;
import team58.healthy.dto.CheckupDTO;
import team58.healthy.dto.CheckupDTOPretty;
import team58.healthy.model.Checkup;
import team58.healthy.model.CheckupRequest;
import team58.healthy.model.Patient;
import team58.healthy.repository.CheckupRepository;
import team58.healthy.security.TokenUtils;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Service
public class CheckupService {

    @Autowired
    private TokenUtils tokenUtils;

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

    @Autowired
    private CheckupRequestService checkupRequestService;

    @Autowired
    private EmailService emailService;

    public List<Checkup> findAll() {
        return checkupRepository.findAll();
    }

    public List<Checkup> findAllOnDate(Date date) {
        return checkupRepository.findAllOnDate(date);
    }

    public Checkup save(Checkup checkup) {
        return checkupRepository.save(checkup);
    }

    public List<Checkup> findAllOnDateByDoctor(Date date, Long doctorId) {
        return checkupRepository.findAllOnDateByDoctor(date, doctorId);
    }

    public List<Checkup> findAllByClinic(Long id){
        return checkupRepository.findByClinicId(id);
    }

    public List<Checkup> findAllByHall(Long id) {
        return checkupRepository.findAllByHall(id);
    }

    public List<Checkup> findForOneClick(Date start, Date end, Long idd, Long idh) {
        return checkupRepository.findAllByDateAndDoctor(start, end, idd, idh);
    }

    public List<Checkup> findAllByHallAndDate(Long id, Date date) {
        return checkupRepository.findAllByHallAndDate(id, date);
    }

    public List<Checkup> findForCheckupSchedule(Date startDate, Date endDate, Long hallId) {
        return checkupRepository.findAllForCheckupSchedule(startDate, endDate, hallId);
    }

    public String delete(String token, Long id) {
        String patientUsername = tokenUtils.getUsernameFromToken(token);
        Patient pat = patientService.findByEmail(patientUsername);
        CheckupRequest cr = checkupRequestService.findOne(id);
        if (cr.getPatient().getId() == pat.getId()) {
            checkupRequestService.delete(cr);
            return "Checkup was canceled.";
        }
        return "You are not allowed to cancel this checkup.";
    }

    public CheckupDTO saveCheckup(String token, Long id, Long hid) {
        String patientUsername = tokenUtils.getUsernameFromToken(token);
        Patient pat = patientService.findByEmail(patientUsername);
        CheckupRequest cr = checkupRequestService.findOne(id);
        if (cr.getPatient().getId() == pat.getId()) {
            Checkup checkup = new Checkup();
            checkup.setClinic(cr.getClinic());
            checkup.setPatient(pat);
            checkup.setCheckupType(cr.getCheckupType());
            checkup.setHall(hallService.findOne2(hid));
            checkup.setDoctor(cr.getDoctor());
            checkup.setEndDate(cr.getEndDate());
            checkup.setStartDate(cr.getStartDate());
            return new CheckupDTO(checkupRepository.save(checkup));
        }
        return null;
    }

    public void saveFromLoop(Checkup checkup)
    {
        checkupRepository.save(checkup);
    }

    public List<Checkup> findByType(Long id) {
        return checkupRepository.findAllByCheckupTypeId(id);
    }

    public List<Checkup> findAllDateAndHall(Date start, Date end, Long id) {
        return checkupRepository.findAllByDateAndHall(start, end, id);
    }

    public List<Checkup> findAllHallAndEndDate(Long id, Date endDate) {
        return checkupRepository.findAllByHallAndEndDate(id, endDate);
    }

    public Boolean sendNotif(CheckupDTO checkupDTO, Long rid) throws MessagingException {
        List<Checkup> checkList = findForCheckupSchedule(checkupDTO.getStartDate(), checkupDTO.getEndDate(), checkupDTO.getHallId());
        if (checkList.isEmpty()) {
            Checkup c = new Checkup();
            // c.setStartDate(checkupDTO.getStartDate());
            // c.setEndDate(checkupDTO.getEndDate());
            // c.setDoctor(doctorService.findOne2(checkupDTO.getDoctorId()));
            c.setHall(hallService.findOne2(checkupDTO.getHallId()));
            // c.setCheckupType(checkupTypeService.findOne(checkupDTO.getCheckupTypeId()));
            c.setPatient(patientService.getOne(checkupDTO.getPatientId()));

            CheckupRequest cr = checkupRequestService.findOne(rid);
            cr.setOnWait(true);
            checkupRequestService.update(cr);

            String patientUsername = c.getPatient().getUsername();
            String requestId = rid.toString();

            String patientUsernameToken = tokenUtils.generateToken(patientUsername);

            emailService.sendHtmlMail("isaprojektovanjeUsers@gmail.com", "Checkups", "<html><body>Clinic admin assigned you checkup request hall: " + c.getHall().getNumber() + ".<br></br><a href=\"http://localhost:8080/api/checkups/confirm/token:" + patientUsernameToken + "/request:" + requestId + "/hall:" + checkupDTO.getHallId() + "\">Confirm<a/><a href=\"http://localhost:8080/api/checkups/cancel/token:" + patientUsernameToken + "/request:" + requestId + "/hall:" + checkupDTO.getHallId() + "\">Cancel</a></body></html>");
            emailService.sendSimpleMail("isaprojektovanjeUsers@gmail.com", "Checkups", "Checkup has added.");

            return true;
        }
        return false;
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

    public CheckupDTO findForCheckupStart(Long did,Long pid)
    {
        Calendar cal = Calendar.getInstance();
        Date now = cal.getTime();
        Checkup ret = checkupRepository.findForCheckupStart(did,pid,now);
        if(ret != null)
        {
            return new CheckupDTO(ret);
        }
        return null;
    }

    public List<CheckupDTO> checkMedicalRecord(Long did,Long pid)
    {
        Calendar cal = Calendar.getInstance();
        Date now = cal.getTime();
        List<Checkup> checks = checkupRepository.findByDoctorPatientAndDate(did,pid,now);
        List<CheckupDTO> dtos = new ArrayList<>();
        for(Checkup c : checks)
        {
            dtos.add(new CheckupDTO(c));
        }
        return dtos;
    }

    public List<CheckupDTO> getAllCheckups() {
        List<Checkup> checkups = findAll();
        List<CheckupDTO> checkupDTOS = new ArrayList<>();
        for (Checkup checkup : checkups)
            checkupDTOS.add(new CheckupDTO(checkup));

        return checkupDTOS;
    }

    public List<CheckupDTO> getAllCheckupsOnDate(int y, int m, int d) {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(y, m - 1, d);
        List<Checkup> checkups = findAllOnDate(cal.getTime());
        List<CheckupDTO> checkupDTOS = new ArrayList<>();
        for (Checkup checkup : checkups) {
            checkupDTOS.add(new CheckupDTO(checkup));
        }

        return checkupDTOS;
    }


}
