package team58.healthy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team58.healthy.dto.MedicalRecordDTO;
import team58.healthy.dto.PatientDTO;
import team58.healthy.model.Patient;
import team58.healthy.repository.PatientRepository;
import team58.healthy.security.TokenUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private TokenUtils tokenUtils;

    public List<PatientDTO> search(String name, String lastName, String healthInsuranceId)
    {
        List<PatientDTO> ret = new ArrayList<>();
        List<Patient> patients = patientRepository.searchAll(name,lastName,healthInsuranceId);

        List<PatientDTO> dtos = new ArrayList<>();
        for(Patient p : patients)
        {
            ret.add(new PatientDTO(p));
        }

        return ret;
    }


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

    public Patient findByEmail(String email) { return patientRepository.findByEmail(email); }

    public Patient save(Patient patient) { return patientRepository.save(patient); }

    public PatientDTO getUser(String token) {
        String email = tokenUtils.getUsernameFromToken(token.substring(7));
        Patient patient = findByEmail(email);
        return new PatientDTO(patient);
    }

    public MedicalRecordDTO getMedicalFromUser(String token) {
        String email = tokenUtils.getUsernameFromToken(token.substring(7));
        Patient patient = findByEmail(email);
        return new MedicalRecordDTO(patient.getMedicalRecord());
    }

}
