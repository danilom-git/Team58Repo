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
            Long idc = checkupTypeRepository.findIdOfCheckup(c.getId());
            double price = checkupTypeRepository.findPriceOfCheckup(idc,id);
            dtos.add( new CheckupTypeViewDTO(idc,c.getName(),price));

        }
        return dtos;
    }

    public List<CheckupTypeViewDTO> findByClinicFalse(Long id)
    {
        List<CheckupType> ct = checkupTypeRepository.findAllByClinicIdFalse(id);
        List<CheckupTypeViewDTO> dtos = new ArrayList<CheckupTypeViewDTO>();
        for(CheckupType c: ct)
        {
            Long idc = checkupTypeRepository.findIdOfCheckup(c.getId());
            double price = checkupTypeRepository.findPriceOfCheckup(idc,id);
            dtos.add( new CheckupTypeViewDTO(idc,c.getName(),price));
        }
        return dtos;
    }
}
