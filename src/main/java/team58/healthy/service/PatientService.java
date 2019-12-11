package team58.healthy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team58.healthy.dto.PatientDTO;
import team58.healthy.model.Patient;
import team58.healthy.repository.PatientRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public List<PatientDTO> getAll()
    {
        List<Patient> patients = patientRepository.findAll();
        List<PatientDTO> dtos = new ArrayList<>();
        for(Patient p : patients)
        {
            dtos.add(new PatientDTO(p));
        }
        return dtos;
    }

    public PatientDTO getOneById(Long id)
    {
        return new PatientDTO(patientRepository.findById(id).orElseGet(null));
    }

}
