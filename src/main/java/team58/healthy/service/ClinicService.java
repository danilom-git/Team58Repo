package team58.healthy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import team58.healthy.model.Checkup;
import team58.healthy.model.CheckupType;
import team58.healthy.model.Clinic;
import team58.healthy.model.Doctor;
import team58.healthy.repository.CheckupRepository;
import team58.healthy.repository.ClinicRepository;
import team58.healthy.repository.DoctorRepository;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class ClinicService {

    @Autowired
    private ClinicRepository clinicRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private CheckupRepository checkupRepository;

    public List<Clinic> findAll() { return clinicRepository.findAll(); }

    public Page<Clinic> findAll(Pageable pageable) { return clinicRepository.findAll(pageable); }

    public List<Clinic> findAllWithCheckupType(Long checkupTypeId) {
        return clinicRepository.findAllWithCheckupTypeId(checkupTypeId);
    }

    public List<Clinic> findAllWithCheckupTypeOnDate(Long checkupTypeId, Date date) {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTime(date);

        //TODO: to be replaced by doctor work hours and a check if he is on holiday or leave
        int startHours = 7;
        int startMinutes = 0;
        int endHours = 15;
        int endMinutes = 0;
        int durationMinutes = 30;

        List<Clinic> clinics = clinicRepository.findAllWithCheckupTypeId(checkupTypeId);
        List<Clinic> availableClinics = new ArrayList<>();

        for (Clinic clinic : clinics) {
            System.out.println("\t\tCurrent clinic: " + clinic.getId());

            List<Doctor> doctors = doctorRepository.findAllByClinicAndCheckupType(clinic.getId(), checkupTypeId);
            boolean hasAvailableDoctor = false;

            for (Doctor doctor : doctors) {
                System.out.println("\t\t\tCurrent doctor: " + doctor.getId());

                List<Checkup> checkups = checkupRepository.findAllOnDateByDoctor(date, doctor.getId());

                if (checkups.isEmpty()) {
                    hasAvailableDoctor = true;
                    System.out.println("\t\t\tDoctor id: " + doctor.getId().toString() + " has no checkups on " + date.toString());
                    break;
                }

                checkups.sort(Comparator.comparing(Checkup::getStartDate));

                cal.set(Calendar.HOUR_OF_DAY, startHours);
                cal.set(Calendar.MINUTE, startMinutes);
                Date startDate = cal.getTime();

                cal.set(Calendar.HOUR_OF_DAY, endHours);
                cal.set(Calendar.MINUTE, endMinutes);
                Date endDate = cal.getTime();

                // da li ima vremena za pregled pre prvog pregleda ili izmedju pregleda
                for (Checkup checkup : checkups) {
                    long milliseconds = Math.abs(checkup.getStartDate().getTime() - startDate.getTime());
                    long minutes = TimeUnit.MINUTES.convert(milliseconds, TimeUnit.MILLISECONDS);

                    System.out.println("\t\t\t\tCurrent checkup: " + checkup.getId());

                    if (minutes > durationMinutes) {
                        hasAvailableDoctor = true;
                        System.out.println("\t\t\tDoctor id: " + doctor.getId().toString() + " has time between " +
                                startDate.toString() + " and " + checkup.getStartDate().toString());
                        break;
                    }

                    startDate = checkup.getEndDate();
                }

                if (hasAvailableDoctor)
                    break;

                // da li ima vremena posle poslednjeg pregleda
                long milliseconds = Math.abs(endDate.getTime() - startDate.getTime());
                long minutes = TimeUnit.MINUTES.convert(milliseconds, TimeUnit.MILLISECONDS);

                if (minutes > durationMinutes) {
                    hasAvailableDoctor = true;
                    System.out.println("\t\t\tDoctor id: " + doctor.getId().toString() + " has time after last checkup " +
                            startDate.toString() + " and before end of shift " + endDate.toString());
                    break;
                }
            }

            if (hasAvailableDoctor)
                availableClinics.add(clinic);
        }

        return availableClinics;
    }
}