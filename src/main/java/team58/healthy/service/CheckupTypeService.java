package team58.healthy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team58.healthy.dto.CheckupTypeDTO;
import team58.healthy.dto.ClinicCheckupTypeDTO;
import team58.healthy.model.CheckupType;
import team58.healthy.model.ClinicCheckupType;
import team58.healthy.repository.CheckupTypeRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CheckupTypeService {

    @Autowired
    private CheckupTypeRepository checkupTypeRepository;

    public CheckupType findOne(Long id){return checkupTypeRepository.findById(id).orElseGet(null);}

    public List<CheckupType> findAll() { return checkupTypeRepository.findAll(); }

    public CheckupType findById(Long id) { return checkupTypeRepository.findById(id).orElseGet(null); }

    public List<ClinicCheckupTypeDTO> findByClinic(Long id)
    {
        List<CheckupType> ct = checkupTypeRepository.findAllByClinicId(id);
        List<ClinicCheckupTypeDTO> dtos = new ArrayList<ClinicCheckupTypeDTO>();
        for(CheckupType c: ct)
        {
            double price = checkupTypeRepository.findPriceOfCheckup(c.getId(),id);
            dtos.add( new ClinicCheckupTypeDTO(c.getId(),c.getName(),price));
        }
        return dtos;
    }

    public List<CheckupTypeDTO> findByClinicFalse(Long id)
    {
        List<CheckupType> ct = checkupTypeRepository.findAllByClinicIdFalse(id);
        List<CheckupTypeDTO> dtos = new ArrayList<CheckupTypeDTO>();
        for(CheckupType c: ct)
        {
            dtos.add( new CheckupTypeDTO(c.getId(),c.getName()));
        }
        return dtos;
    }
}
