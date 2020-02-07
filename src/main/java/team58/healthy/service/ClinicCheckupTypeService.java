package team58.healthy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team58.healthy.dto.ClinicCheckupTypeDTO;
import team58.healthy.model.CheckupType;
import team58.healthy.model.Clinic;
import team58.healthy.model.ClinicCheckupType;
import team58.healthy.repository.ClinicCheckupTypeRepository;

@Service
public class ClinicCheckupTypeService {

    @Autowired
    private ClinicCheckupTypeRepository clinicCheckupTypeRepository;

    @Autowired
    private ClinicService clinicService;

    public ClinicCheckupType findByClinicAndCheckupTypeId(Clinic clinic, Long checkupTypeId) {
        return clinicCheckupTypeRepository.findByClinicAndCheckupTypeId(clinic, checkupTypeId);
    }

    public ClinicCheckupTypeDTO save(ClinicCheckupTypeDTO clinicCheckupTypeDTO, Long id)
    {
        ClinicCheckupType clinicCheckupType = new ClinicCheckupType();
        clinicCheckupType.setClinic(clinicService.findById(id));

        CheckupType ct = new CheckupType();
        ct.setId(clinicCheckupTypeDTO.getId());
        ct.setName(clinicCheckupTypeDTO.getName());

        clinicCheckupType.setCheckupType(ct);
        clinicCheckupType.setPrice(clinicCheckupTypeDTO.getPrice());

        return  new ClinicCheckupTypeDTO(clinicCheckupTypeRepository.save(clinicCheckupType));
    }
}
