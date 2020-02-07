package team58.healthy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team58.healthy.dto.CheckupTypeViewDTO;
import team58.healthy.model.CheckupType;
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

    public List<CheckupTypeViewDTO> findByClinic(Long id)
    {
        List<CheckupType> ct = checkupTypeRepository.findAllByClinicId(id);
        List<CheckupTypeViewDTO> dtos = new ArrayList<CheckupTypeViewDTO>();
        for(CheckupType c: ct)
        {
            dtos.add( new CheckupTypeViewDTO(c.getId(),c.getName(),checkupTypeRepository.findPriceOfCheckup(c.getId(),id)));
        }
        return dtos;
    }
}
