package team58.healthy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team58.healthy.dto.OneClickDTO;
import team58.healthy.model.OneClick;
import team58.healthy.repository.OneClickRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class OneClickService {

    @Autowired
    private OneClickRepository oneClickRepository;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private HallService hallService;

    @Autowired
    private CheckupTypeService checkupTypeService;

    @Autowired
    private ClinicService clinicService;

    public List<OneClickDTO> findAll()
    {
        List<OneClick> oneClicks = oneClickRepository.findAll();
        List<OneClickDTO> dtos = new ArrayList<OneClickDTO>();
        for(OneClick o : oneClicks)
        {
            dtos.add(new OneClickDTO(o));
        }
        return dtos;
    }

    public OneClickDTO save(OneClickDTO oneClickDTO){
        OneClick oc = new OneClick();
        oc.setDoctor(doctorService.findOne2(oneClickDTO.getDoctorId()));
        oc.setHall(hallService.findOne2(oneClickDTO.getHallId()));
        oc.setCheckupType(checkupTypeService.findOne(oneClickDTO.getCheckupTypeId()));
        oc.setClinic(clinicService.findById(oneClickDTO.getClinicId()));
        oc.setDuration(oneClickDTO.getDuration());
        oc.setStartTime(oneClickDTO.getStartTime());
        oc.setEndTime(oneClickDTO.getEndTime());
        oc.setPrice(oneClickDTO.getPrice());
        return new OneClickDTO(oneClickRepository.save(oc));
    }
}
