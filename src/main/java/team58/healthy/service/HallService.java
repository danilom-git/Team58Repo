package team58.healthy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team58.healthy.dto.CheckupDTO;
import team58.healthy.dto.FirstDateAvailableDTO;
import team58.healthy.dto.HallDTO;
import team58.healthy.dto.HallSearchDTO;
import team58.healthy.model.Checkup;
import team58.healthy.model.CheckupRequest;
import team58.healthy.model.Hall;
import team58.healthy.repository.HallRepository;

import java.util.ArrayList;
import java.util.Calendar;
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

    @Autowired
    private CheckupRequestService checkupRequestService;

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

    public FirstDateAvailableDTO firstAvailable(Long id,Long rid)
    {
        CheckupRequest cr = checkupRequestService.findOne(rid);
        List<Checkup> checkups = checkupService.findAllDateAndHall(cr.getStartDate(),cr.getEndDate(),id);

        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTime(cr.getStartDate());
        Calendar cal1 = Calendar.getInstance();
        cal1.clear();
        cal1.setTime(cr.getEndDate());

        int difH = cal1.get(Calendar.HOUR_OF_DAY) - cal.get(Calendar.HOUR_OF_DAY);
        int difM = cal1.get(Calendar.MINUTE) - cal.get(Calendar.MINUTE);

        if(checkups.isEmpty())
        {
            return new FirstDateAvailableDTO(cr.getStartDate(),cr.getEndDate());
        }else
        {
            List<Checkup> allOnDate = checkupService.findAllHallAndEndDate(id,cr.getEndDate());
            for(Checkup c: allOnDate)
            {
                Calendar calendar = Calendar.getInstance();
                calendar.clear();
                calendar.setTime(c.getEndDate());
                calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH),calendar.get(Calendar.HOUR_OF_DAY) + difH,calendar.get(Calendar.MINUTE) + difM);
                Date endDate = calendar.getTime();
                List<Checkup> checks = checkupService.findAllDateAndHall(c.getEndDate(),endDate,id);
                if(checks.isEmpty())
                {
                    return new FirstDateAvailableDTO(c.getEndDate(),endDate);
                }
            }
        }
        return  null;
    }

    public List<HallSearchDTO> findOnDateAndNameOrNumber(Date date, String name, String number)
    {
        // List<Hall> halls  = hallRepository.getHallsOnDateAndName(date,name, number);
        List<Hall> halls  = hallRepository.getHallsOnNumberAndName(name, number);
        List<HallSearchDTO> dtos = new ArrayList<HallSearchDTO>();
        for(Hall h : halls)
        {
            List<Checkup> curr = checkupService.findAllByHallAndDate(h.getId(),date);
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
