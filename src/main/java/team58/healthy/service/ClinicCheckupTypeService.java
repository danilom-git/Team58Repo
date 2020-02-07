package team58.healthy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team58.healthy.dto.ClinicCheckupTypeDTO;
import team58.healthy.model.Checkup;
import team58.healthy.model.CheckupType;
import team58.healthy.model.Clinic;
import team58.healthy.model.ClinicCheckupType;
import team58.healthy.repository.ClinicCheckupTypeRepository;

import java.util.List;

@Service
public class ClinicCheckupTypeService {

    @Autowired
    private CheckupService checkupService;

    @Autowired
    private ClinicCheckupTypeRepository clinicCheckupTypeRepository;

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private CheckupTypeService checkupTypeService;

    public ClinicCheckupType findByClinicAndCheckupTypeId(Clinic clinic, Long checkupTypeId) {
        return clinicCheckupTypeRepository.findByClinicAndCheckupTypeId(clinic, checkupTypeId);
    }

    public ClinicCheckupTypeDTO update(Long id,Long cid,double price)
    {
        ClinicCheckupType cct = clinicCheckupTypeRepository.findByClinicAndCheckupTypeId(clinicService.findById(cid), id);
        List<Checkup> checks = checkupService.findByType(id);
        if(cct != null && checks.isEmpty())
        {
            cct.setPrice(price);
            return new ClinicCheckupTypeDTO(clinicCheckupTypeRepository.save(cct));
        }
        return null;
    }

    public Boolean delete(Long id,Long cid) {
        ClinicCheckupType cct = clinicCheckupTypeRepository.findByClinicAndCheckupTypeId(clinicService.findById(cid), id);
        List<Checkup> checks = checkupService.findByType(id);
        if (cct != null && checks.isEmpty()) {
            cct.setCheckupType(null);
            cct.setClinic(null);
            clinicCheckupTypeRepository.delete(cct);
            return true;
        }
        return false;
    }
    public ClinicCheckupTypeDTO save(ClinicCheckupTypeDTO clinicCheckupTypeDTO, Long id)
    {
        ClinicCheckupType clinicCheckupType = new ClinicCheckupType();
        clinicCheckupType.setClinic(clinicService.findById(id));
        clinicCheckupType.setCheckupType(checkupTypeService.findById(clinicCheckupTypeDTO.getId()));
        clinicCheckupType.setPrice(clinicCheckupTypeDTO.getPrice());

        return new ClinicCheckupTypeDTO(clinicCheckupTypeRepository.save(clinicCheckupType));
    }
}
