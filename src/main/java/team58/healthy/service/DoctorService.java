package team58.healthy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team58.healthy.model.Doctor;
import team58.healthy.repository.DoctorRepository;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor findOne(Long id)
    {
        return doctorRepository.findById(id).orElseGet(null);
    }

    public List<Doctor> findAllByName(String name)
    {
        return doctorRepository.findAllByName(name);
    }

    public List<Doctor> findAll()
    {
        return doctorRepository.findAll();
    }
}
