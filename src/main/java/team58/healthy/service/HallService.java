package team58.healthy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team58.healthy.dto.CheckupDTO;
import team58.healthy.dto.HallDTO;
import team58.healthy.dto.HallSearchDTO;
import team58.healthy.model.Checkup;
import team58.healthy.model.Hall;
import team58.healthy.repository.HallRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class HallService {
    @Autowired
    private HallRepository hallRepository;

    @Autowired
    private CheckupService checkupService;

    @Autowired
    private ClinicService clinicService;

    public HallDTO findOne(Long id){
        return new HallDTO( hallRepository.findById(id).orElseGet(null));
    }

    public Hall findOne2(Long id){return hallRepository.findById(id).orElseGet(null);}

    public List<HallDTO> findAll(){
        List<Hall> halls  = hallRepository.findAll();
        List<HallDTO> hallsDTO = new ArrayList<HallDTO>();
        for(Hall h : halls)
        {
            hallsDTO.add(new HallDTO(h));
        }
        return hallsDTO;
    }

    public List<HallDTO> findAllByClinicId(Long id){
        List<Hall> halls  = hallRepository.findAllByClinicId(id);
        List<HallDTO> hallsDTO = new ArrayList<HallDTO>();
        for(Hall h : halls)
        {
            hallsDTO.add(new HallDTO(h));
        }
        return hallsDTO;
    }

    public boolean remove(Long id){
        Hall hall = hallRepository.findById(id).orElseGet(null);
        if(hall != null )
        {
           List<Checkup> checkupHalls = checkupService.findAllByHall(hall.getId());
           if(checkupHalls.isEmpty()) {
               hall.setClinic(null);
               hallRepository.deleteById(id);
               return true;
           }
            hallRepository.deleteById(id);

            return false;
        }else
            return false;
    }

    public HallDTO update(HallDTO hallDTO)
    {
        Hall hall = hallRepository.findById(hallDTO.getId()).orElseGet(null);
        if(hall != null) {
            if(!hallDTO.getName().equals("") && !hallDTO.getNumber().equals("")) {
                hall.setName(hallDTO.getName());
                hall.setNumber(hallDTO.getNumber());
                hall.setId(hallDTO.getId());
                hall.setClinic(clinicService.findById(hallDTO.getClinicId()));
            }
        }
        return new HallDTO(hallRepository.save(hall));
    }

    public HallDTO save(HallDTO hallDTO){
        Hall hall = new Hall();
        hall.setName(hallDTO.getName());
        hall.setNumber(hallDTO.getNumber());
        hall.setClinic(clinicService.findById(hallDTO.getClinicId()));

        return new HallDTO(hallRepository.save(hall));
    }

    public List<HallSearchDTO> findOnDateAndNameOrNumber(Date date, String name, String number)
    {
        List<Hall> halls  = hallRepository.getHallsOnDateAndName(date,name, number);
        List<HallSearchDTO> dtos = new ArrayList<HallSearchDTO>();
        for(Hall h : halls)
        {
            List<Checkup> curr = checkupService.findAllByHall(h.getId());
            List<CheckupDTO> currDtos = new ArrayList<CheckupDTO>();

            for(Checkup c : curr)
            {
                CheckupDTO dto = new CheckupDTO(c);
                currDtos.add(dto);
            }

            HallSearchDTO dt = new HallSearchDTO(h,currDtos);
            dtos.add(dt);
        }
        return dtos;
    }
}
