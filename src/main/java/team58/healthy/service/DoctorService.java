package team58.healthy.service;

import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import team58.healthy.dto.DoctorDTO;
import team58.healthy.model.Checkup;
import team58.healthy.model.Doctor;
import team58.healthy.repository.DoctorRepository;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private CheckupService checkupService;


    public DoctorDTO findOne(Long id)
    {
        Doctor doc = doctorRepository.findById(id).orElseGet(null);
        return new DoctorDTO(doc);
    }

    public List<Doctor> findAllByName(String name)
    {
        return doctorRepository.findAllByName(name);
    }

    public List<Doctor> findAll()
    {
        return doctorRepository.findAll();
    }

    public List<Doctor> findAllByClinicAndCheckupType(Long clinicId, Long checkupTypeId) {
        return doctorRepository.findAllByClinicAndCheckupType(clinicId, checkupTypeId);
    }

    public boolean remove(Long id){
        Doctor doctor = doctorRepository.findById(id).orElseGet(null);

        System.out.println(doctor.toString());
        if(doctor != null && (doctor.getClinic() == null) && doctor.getCheckups().isEmpty())
        {
            doctorRepository.deleteById(id);
            return true;
        }else
        {
            return false;
        }

    }

    public Doctor save(Doctor doctor){return doctorRepository.save(doctor);}

    public List<Doctor> findAllByClinic(Long clinicId) { return doctorRepository.findAllByClinicId(clinicId); }

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

        //TODO: to be replaced by doctor work hours and a check if he is on holiday or leave
        int startHours = 7;
        int startMinutes = 0;
        int endHours = 15;
        int endMinutes = 0;
        int durationMinutes = 30;

        cal.set(Calendar.HOUR_OF_DAY, startHours);
        cal.set(Calendar.MINUTE, startMinutes);
        Date startDate = cal.getTime();

        cal.set(Calendar.HOUR_OF_DAY, endHours);
        cal.set(Calendar.MINUTE, endMinutes);
        Date endDate = cal.getTime();

        List<Checkup> checkups = checkupService.findAllOnDateByDoctor(date, doctor.getId());

        if (checkups.isEmpty()) {
            free.add(new Pair<>(startDate, endDate));
//            System.out.println("\t\t\tDoctor id: " + doctor.getId().toString() + " has no checkups on " + date.toString());
            return free;
        }

        checkups.sort(Comparator.comparing(Checkup::getStartDate));

        // da li ima vremena za pregled pre prvog pregleda ili izmedju pregleda
        for (Checkup checkup : checkups) {
            long milliseconds = Math.abs(checkup.getStartDate().getTime() - startDate.getTime());
            long minutes = TimeUnit.MINUTES.convert(milliseconds, TimeUnit.MILLISECONDS);

//            System.out.println("\t\t\t\tCurrent checkup: " + checkup.getId());

            if (minutes > durationMinutes) {
                free.add(new Pair<>(startDate, checkup.getStartDate()));
//                System.out.println("\t\t\tDoctor id: " + doctor.getId().toString() + " has time between " +
//                        startDate.toString() + " and " + checkup.getStartDate().toString());
            }

            startDate = checkup.getEndDate();
        }

        // da li ima vremena posle poslednjeg pregleda
        long milliseconds = Math.abs(endDate.getTime() - startDate.getTime());
        long minutes = TimeUnit.MINUTES.convert(milliseconds, TimeUnit.MILLISECONDS);

        if (minutes > durationMinutes) {
            free.add(new Pair<>(startDate, endDate));
//            System.out.println("\t\t\tDoctor id: " + doctor.getId().toString() + " has time after last checkup " +
//                    startDate.toString() + " and before end of shift " + endDate.toString());
        }

        return free;
    }
}
