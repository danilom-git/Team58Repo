package team58.healthy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team58.healthy.model.Patient;
import team58.healthy.repository.PatientRepository;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Patient findByEmail(String email) { return patientRepository.findByEmail(email); }

    public Patient save(Patient patient) { return patientRepository.save(patient); }
}
