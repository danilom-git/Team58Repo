package team58.healthy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team58.healthy.dto.CheckupDTO;
import team58.healthy.dto.CheckupRequestDTO;
import team58.healthy.dto.CheckupRequestViewDTO;
import team58.healthy.dto.FirstDateAvailableDTO;
import team58.healthy.model.*;
import team58.healthy.repository.CheckupRequestRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

    @Autowired
    private PatientService patientService;

    public List<CheckupRequest> findAll() {
        return checkupRequestRepository.findAll();
    }

    public CheckupRequestDTO save(CheckupRequestDTO checkupRequestDTO) {
        CheckupRequest checkupRequest = new CheckupRequest();

        Date startDate = checkupRequestDTO.getStartDate();
        Date endDate = checkupRequestDTO.getEndDate();
        Clinic clinic = clinicService.findById(checkupRequestDTO.getClinicId());
        Doctor doctor = doctorService.findById(checkupRequestDTO.getDoctorId());
        CheckupType checkupType = checkupTypeService.findById(checkupRequestDTO.getCheckupTypeId());
        Patient patient = patientService.getOne(checkupRequestDTO.getPatientId());
        if (checkupRequestDTO.getStartDate() == null || checkupRequestDTO.getEndDate() == null || clinic == null ||
            doctor == null || checkupType == null || patient == null)
            return null;

        if (endDate.before(startDate))
            return null;

        checkupRequest.setStartDate(checkupRequestDTO.getStartDate());
        checkupRequest.setEndDate(checkupRequestDTO.getEndDate());
        checkupRequest.setClinic(clinic);
        checkupRequest.setDoctor(doctor);
        checkupRequest.setCheckupType(checkupType);
        checkupRequest.setPatient(patient);
        checkupRequest.setOnWait(false);

        CheckupRequestDTO saved;
        try {
            saved = new CheckupRequestDTO(checkupRequestRepository.save(checkupRequest));
        } catch (Exception e) { // unique identifier constraint
            e.printStackTrace();
            return null;
        }

        return saved;
    }

    public List<CheckupRequestViewDTO> findByClinic(Long id) {
        List<CheckupRequest> req = checkupRequestRepository.findAllByClinicIdAndOnWait(id);
        List<CheckupRequestViewDTO> dtos = new ArrayList<CheckupRequestViewDTO>();
        for (CheckupRequest cr : req) {
            dtos.add(new CheckupRequestViewDTO(cr));
        }
        return dtos;
    }

    public CheckupRequestDTO changeDate(FirstDateAvailableDTO firstDateAvailableDTO,Long id)
    {
        CheckupRequest cr = checkupRequestRepository.findById(id).orElse(null);
        if (cr == null)
            return null;

        cr.setEndDate(firstDateAvailableDTO.getEndDate());
        cr.setStartDate(firstDateAvailableDTO.getStartDate());
        return new CheckupRequestDTO(checkupRequestRepository.save(cr));
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
        return  checkupRequestRepository.findById(id).orElse(null);
    }

    public CheckupRequestViewDTO getOne(Long id)
    {
        CheckupRequest cr = checkupRequestRepository.findById(id).orElse(null);
        if(cr != null)
            return new CheckupRequestViewDTO(cr);

        return null;
    }

    public List<CheckupRequestDTO> getAll() {
        List<CheckupRequest> checkupRequests = findAll();
        List<CheckupRequestDTO> checkupRequestDTOS = new ArrayList<>();
        for (CheckupRequest checkupRequest : checkupRequests)
            checkupRequestDTOS.add(new CheckupRequestDTO(checkupRequest));

        return checkupRequestDTOS;
    }
}
