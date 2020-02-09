package team58.healthy.service;

import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team58.healthy.dto.DoctorDTO;
import team58.healthy.dto.DoctorWithAvailableTimeDTO;
import team58.healthy.model.Checkup;
import team58.healthy.model.Doctor;
import team58.healthy.repository.DoctorRepository;
import team58.healthy.security.TokenUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class DoctorService {

    @Autowired
    private UserService userService;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private CheckupService checkupService;

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AuthorityService authorityService;

    public DoctorDTO findOne(Long id)
    {
        Doctor doc = doctorRepository.findById(id).orElse(null);
        if (doc == null)
            return null;
        return new DoctorDTO(doc);
    }

    public Doctor findOne2(Long id)
    {
        return doctorRepository.findById(id).orElse(null);
    }

    public List<Doctor> findAllByName(String name)
    {
        return doctorRepository.findAllByName(name);
    }

    public List<DoctorDTO> findAll()
    {
        List<Doctor> doctors = doctorRepository.findAll();

        List<DoctorDTO> doctorsDTO = new ArrayList<>();
        for(Doctor d : doctors)
            doctorsDTO.add(new DoctorDTO(d));
        return doctorsDTO;
    }

    public Doctor findById(Long id) { return doctorRepository.findById(id).orElse(null); }

    public List<Doctor> findAllByClinicAndCheckupType(Long clinicId, Long checkupTypeId) {
        return doctorRepository.findAllByClinicAndCheckupType(clinicId, checkupTypeId);
    }

    public boolean remove(Long id){
        Doctor doctor = doctorRepository.findById(id).orElse(null);

        if (doctor == null)
            return false;

        System.out.println(doctor.toString());
        doctor.setClinic(null);
        doctor.setCheckups(null);
        doctor.setCheckupTypes(null);
        doctor.setAuthority(null);
        doctor.setCheckupTypes(null);
        doctorRepository.deleteById(id);
        return true;
    }

    public DoctorDTO update(DoctorDTO doctorDTO)
    {
        if(findOne(doctorDTO.getId()) != null)
        {
            if(!doctorDTO.getName().equals("") && !doctorDTO.getLastName().equals("") && doctorDTO.getWorkingTime() != 0) {
                Doctor doctor = new Doctor();
                doctor.setId(doctorDTO.getId());
                doctor.setClinic(clinicService.findById(doctorDTO.getClinicId()));
                doctor.setWorkingTime(doctorDTO.getWorkingTime());
                doctor.setRating(doctorDTO.getRating());
                doctor.setName(doctorDTO.getName());
                doctor.setLastName(doctorDTO.getLastName());
                doctor.setFirstPasswordChanged(doctorDTO.getFirstPasswordChanged());
                doctor.setEmail(doctorDTO.getEmail());
                return new DoctorDTO( doctorRepository.save(doctor));
            }

            return null;

        }
        return null;
    }

    public Doctor save(DoctorDTO doctorDTO){
        Doctor doctor = new Doctor();
        doctor.setName(doctorDTO.getName());
        doctor.setLastName(doctorDTO.getLastName());
        doctor.setWorkingTime(doctorDTO.getWorkingTime());
        doctor.setClinic(clinicService.findById(doctorDTO.getClinicId()));
        doctor.setRating(0);
        doctor.setEmail(doctorDTO.getEmail());
        doctor.setFirstPasswordChanged(false);
        doctor.setPassword(userService.encode("123"));
        long auth = 2;
        doctor.setAuthority(authorityService.findById(auth));
        emailService.sendSimpleMail("isaprojektovanjeUsers@gmail.com","Account added","You are added to system. Temporal password: 123");
        return doctorRepository.save(doctor);
    }

    public Doctor save(Doctor doctor) { return doctorRepository.save(doctor); }

    public List<Doctor> findAllByClinic(Long clinicId) { return doctorRepository.findAllByClinicId(clinicId); }

    public List<DoctorDTO> findAllByClinicDTO(Long id){
        List<Doctor> doctors = doctorRepository.findAllByClinicId(id);
        List<DoctorDTO> dtos = new ArrayList<>();
        for(Doctor d : doctors)
        {
            dtos.add(new DoctorDTO(d));
        }
        return dtos;
    }

    public List<Doctor> findAllByClinicWithCheckupTypeOnDate(Long clinicId, Long checkupTypeId, Date date) {
        List<Doctor> doctors = findAllByClinicAndCheckupType(clinicId, checkupTypeId);
        List<Doctor> availableDoctors = new ArrayList<>();
        for (Doctor doctor : doctors)
            if (!availableTimeOnDate(doctor, date).isEmpty())
                availableDoctors.add(doctor);

        return availableDoctors;
    }

    public List<Pair<Date, Date>> availableTimeOnDate(Doctor doctor, Date date) {
        List<Pair<Date, Date>> free = new ArrayList<>();

        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTime(date);

        int startHours = 7;
        int startMinutes = 0;
        int endHours = 19;
        int endMinutes = 0;
        int durationMinutes = 60;

        cal.set(Calendar.HOUR_OF_DAY, startHours);
        cal.set(Calendar.MINUTE, startMinutes);
        Date startDate = cal.getTime();

        cal.set(Calendar.HOUR_OF_DAY, endHours);
        cal.set(Calendar.MINUTE, endMinutes);
        Date endDate = cal.getTime();

        List<Checkup> checkups = checkupService.findAllOnDateByDoctor(date, doctor.getId());

        if (checkups.isEmpty()) {
            free.add(new Pair<>(startDate, endDate));
            return free;
        }

        checkups.sort(Comparator.comparing(Checkup::getStartDate));

        // da li ima vremena za pregled pre prvog pregleda ili izmedju pregleda
        for (Checkup checkup : checkups) {
            long milliseconds = Math.abs(checkup.getStartDate().getTime() - startDate.getTime());
            long minutes = TimeUnit.MINUTES.convert(milliseconds, TimeUnit.MILLISECONDS);

            if (minutes > durationMinutes) {
                free.add(new Pair<>(startDate, checkup.getStartDate()));
            }

            startDate = checkup.getEndDate();
        }

        // da li ima vremena posle poslednjeg pregleda
        long milliseconds = Math.abs(endDate.getTime() - startDate.getTime());
        long minutes = TimeUnit.MINUTES.convert(milliseconds, TimeUnit.MILLISECONDS);

        if (minutes > durationMinutes) {
            free.add(new Pair<>(startDate, endDate));
        }

        return free;
    }

    public Doctor findByEmail(String email) { return doctorRepository.findByEmail(email); }

    public DoctorDTO findByToken(String token){
        String email = tokenUtils.getUsernameFromToken(token);
        Doctor d  = this.findByEmail(email);
        return new DoctorDTO( d );
    }

    public DoctorDTO getFromToken(String token) {
        String email = tokenUtils.getUsernameFromToken(token.substring(7));
        Doctor doc = findByEmail(email);
        return new DoctorDTO(doc);
    }

    public List<DoctorDTO> getAllInClinicWithCheckupType(Long clinicId, Long checkupTypeId) {
        List<Doctor> doctors = findAllByClinicAndCheckupType(clinicId, checkupTypeId);
        List<DoctorDTO> doctorDTOS = new ArrayList<>();
        for (Doctor doctor : doctors)
            doctorDTOS.add(new DoctorDTO(doctor));

        return doctorDTOS;
    }

    public List<DoctorWithAvailableTimeDTO> getAllInClinicWithCheckupTypeOnDate(Long clinicId, Long checkupTypeId, int y, int m, int d) {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(y, m - 1, d);
        List<Doctor> doctors = findAllByClinicWithCheckupTypeOnDate(clinicId, checkupTypeId, cal.getTime());

        List<DoctorWithAvailableTimeDTO> doctorDTOS = new ArrayList<>();
        for (Doctor doctor : doctors)
            doctorDTOS.add(new DoctorWithAvailableTimeDTO(new DoctorDTO(doctor), availableTimeOnDate(doctor, cal.getTime())));

        return doctorDTOS;
    }
}
