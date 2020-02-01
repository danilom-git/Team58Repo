package team58.healthy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team58.healthy.dto.OneClickDTO;
import team58.healthy.dto.OneClickViewDTO;
import team58.healthy.model.Checkup;
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

    @Autowired
    private CheckupService checkupService;

    public List<OneClickViewDTO> findAll()
    {
        List<OneClick> oneClicks = oneClickRepository.findAll();
        List<OneClickViewDTO> dtos = new ArrayList<OneClickViewDTO>();
        for(OneClick o : oneClicks)
        {
            dtos.add(new OneClickViewDTO(o));
        }
        return dtos;
    }

    public List<OneClickViewDTO> findAllByClinic(Long id)
    {
        List<OneClick> oneClicks = oneClickRepository.findAllByClinicId(id);
        List<OneClickViewDTO> dtos = new ArrayList<OneClickViewDTO>();
        for(OneClick o : oneClicks)
        {
            dtos.add(new OneClickViewDTO(o));
        }
        return dtos;
    }

    public OneClickDTO save(OneClickDTO oneClickDTO){
        OneClick oc = new OneClick();

        List<Checkup> checkups = checkupService.findForOneClick(oneClickDTO.getStartTime(),oneClickDTO.getEndTime(),oneClickDTO.getDoctorId(),oneClickDTO.getHallId());

        if(checkups.isEmpty())
        {
            oc.setDoctor(doctorService.findOne2(oneClickDTO.getDoctorId()));
            oc.setHall(hallService.findOne2(oneClickDTO.getHallId()));
            oc.setCheckupType(checkupTypeService.findOne(oneClickDTO.getCheckupTypeId()));
            oc.setClinic(clinicService.findById(oneClickDTO.getClinicId()));
            oc.setDuration(oneClickDTO.getDuration());
            oc.setStartTime(oneClickDTO.getStartTime());
            oc.setEndTime(oneClickDTO.getEndTime());
            oc.setPrice(oneClickDTO.getPrice());
            return new OneClickDTO(oneClickRepository.save(oc));

        }else
        {
            return null;
        }

    }
}
