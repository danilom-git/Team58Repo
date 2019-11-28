package team58.healthy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team58.healthy.model.CheckupType;
import team58.healthy.model.Clinic;
import team58.healthy.model.ClinicCheckupType;
import team58.healthy.repository.ClinicCheckupTypeRepository;

@Service
public class ClinicCheckupTypeService {

    @Autowired
    private ClinicCheckupTypeRepository clinicCheckupTypeRepository;

    public ClinicCheckupType findByClinicAndCheckupTypeId(Clinic clinic, Long checkupTypeId) {
        return clinicCheckupTypeRepository.findByClinicAndCheckupTypeId(clinic, checkupTypeId);
    }
}
