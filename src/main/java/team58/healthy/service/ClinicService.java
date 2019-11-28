package team58.healthy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import team58.healthy.model.Checkup;
import team58.healthy.model.CheckupType;
import team58.healthy.model.Clinic;
import team58.healthy.model.Doctor;
import team58.healthy.repository.ClinicRepository;
import team58.healthy.repository.DoctorRepository;

import java.util.*;

@Service
public class ClinicService {

    @Autowired
    private ClinicRepository clinicRepository;
    @Autowired
    private DoctorRepository doctorRepository;

    public List<Clinic> findAll() { return clinicRepository.findAll(); }

    public Page<Clinic> findAll(Pageable pageable) { return clinicRepository.findAll(pageable); }

    public List<Clinic> findAllWithCheckupType(Long checkupTypeId) {
        return clinicRepository.findAllWithCheckupTypeId(checkupTypeId);
    }

    public List<Clinic> findAllWithCheckupTypeOnDate(Long checkupTypeId, Date date) {
        List<Clinic> clinics = clinicRepository.findAllWithCheckupTypeId(checkupTypeId);
        List<Clinic> availableClinics = new ArrayList<Clinic>();

//        for (Clinic clinic : clinics) {
//            List<Doctor> doctors = doctorRepository.findAllBy
//        }
//
//
//        for (Clinic clinic : clinics) {
//            List<Checkup> checkups = new ArrayList<>(clinic.getCheckups());
//            List<Doctor> doctors = new ArrayList<>();
//            for (Doctor doctor : clinic.getDoctors()) {
//                boolean performing = false;
//                for (CheckupType checkupType : doctor.getCheckupTypes()) {
//                    if (checkupType.getId() == checkupTypeId)
//                        performing = true;
//                }
//
//            }
//        }

        return availableClinics;
    }
}
