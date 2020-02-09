package team58.healthy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import team58.healthy.dto.CheckupDTO;
import team58.healthy.dto.ClinicDTO;
import team58.healthy.dto.ClinicReportDTO;
import team58.healthy.dto.ClinicWithCheckupDTO;
import team58.healthy.model.Checkup;
import team58.healthy.model.CheckupType;
import team58.healthy.model.Clinic;
import team58.healthy.model.ClinicCheckupType;
import team58.healthy.repository.ClinicRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ClinicService {

    @Autowired
    private ClinicRepository clinicRepository;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private ClinicCheckupTypeService clinicCheckupTypeService;

    @Autowired
    private CheckupService checkupService;

    public List<Clinic> findAll() { return clinicRepository.findAll(); }

    public Page<Clinic> findAll(Pageable pageable) { return clinicRepository.findAll(pageable); }

    public Clinic findById(Long id) { return clinicRepository.findById(id).orElseGet(null); }

    public ClinicDTO findByIdDTO(Long id)
    {
        return new ClinicDTO(clinicRepository.findById(id).orElseGet(null));
    }

    public ClinicDTO saveInfo(ClinicDTO clinicDTO)
    {
        Clinic clinic = findById(clinicDTO.getId());
        clinic.setAddress(clinicDTO.getAddress());
        clinic.setCity(clinicDTO.getCity());
        clinic.setCountry(clinicDTO.getCountry());
        clinic.setName(clinicDTO.getName());
        clinic.setxCoord(clinicDTO.getxCoord());
        clinic.setyCoord(clinic.getyCoord());
        return new ClinicDTO(clinicRepository.save(clinic));
    }

    public List<Clinic> findAllWithCheckupType(Long checkupTypeId) {
        return clinicRepository.findAllWithCheckupTypeId(checkupTypeId);
    }

    public List<Clinic> findAllWithCheckupTypeOnDate(Long checkupTypeId, Date date) {
        List<Clinic> clinics = findAll();
        List<Clinic> availableClinics = new ArrayList<>();
        //TODO check whether the clinic supports the the checkup type
        for (Clinic clinic : clinics)
            if (!doctorService.findAllByClinicWithCheckupTypeOnDate(clinic.getId(), checkupTypeId, date).isEmpty())
                availableClinics.add(clinic);

        return availableClinics;
    }

    public List<ClinicDTO> getAllClinics() {
        List<Clinic> clinics = findAll();
        List<ClinicDTO> clinicDTOs = new ArrayList<>();
        for (Clinic clinic : clinics) {
            clinicDTOs.add(new ClinicDTO(clinic));
        }

        return clinicDTOs;
    }

    public List<ClinicWithCheckupDTO> getAllClinicsWithCheckupType(Long checkupTypeId) {
        List<Clinic> clinics = findAllWithCheckupType(checkupTypeId);

        List<ClinicWithCheckupDTO> clinicWithCheckupDTOS = new ArrayList<>();
        for (Clinic clinic : clinics) {
            ClinicCheckupType clinicCheckupType = clinicCheckupTypeService.findByClinicAndCheckupTypeId(clinic, checkupTypeId);
            clinicWithCheckupDTOS.add(new ClinicWithCheckupDTO(new ClinicDTO(clinic), clinicCheckupType));
        }

        return clinicWithCheckupDTOS;
    }

    public List<ClinicWithCheckupDTO> getAllClinicsWithCheckupTypeOnDate(Long checkupTypeId, int y, int m, int d) {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(y, m - 1, d);

        List<Clinic> clinics = findAllWithCheckupTypeOnDate(checkupTypeId, cal.getTime());

        List<ClinicWithCheckupDTO> clinicWithCheckupDTOS = new ArrayList<>();
        for (Clinic clinic : clinics) {
            ClinicCheckupType clinicCheckupType = clinicCheckupTypeService.findByClinicAndCheckupTypeId(clinic, checkupTypeId);
            clinicWithCheckupDTOS.add(new ClinicWithCheckupDTO(new ClinicDTO(clinic), clinicCheckupType));
        }

        return clinicWithCheckupDTOS;
    }

    public ClinicReportDTO getReport(Long id)
    {
        Calendar cal = Calendar.getInstance();
        Clinic clinic = clinicRepository.findById(id).orElse(null);
        List<Checkup> checks = checkupService.findAllByClinic(id);
        List<CheckupDTO> cdots = new ArrayList<CheckupDTO>();
        for(Checkup c : checks)
        {
            if(c.getEndDate().compareTo(cal.getTime()) < 0)
                cdots.add(new CheckupDTO(c));
        }
        return new ClinicReportDTO(clinic,cdots);
    }

}
