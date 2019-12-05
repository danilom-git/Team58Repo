package team58.healthy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team58.healthy.dto.HallDTO;
import team58.healthy.model.Hall;
import team58.healthy.repository.HallRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class HallService {
    @Autowired
    private HallRepository hallRepository;

    public HallDTO findOne(Long id){
        return new HallDTO( hallRepository.findById(id).orElseGet(null));
    }

    public List<HallDTO> findAll(){
        List<Hall> halls  = hallRepository.findAll();
        List<HallDTO> hallsDTO = new ArrayList<HallDTO>();
        for(Hall h : halls)
        {
            hallsDTO.add(new HallDTO(h));
        }
        return hallsDTO;
    }

    public boolean remove(Long id){
        Hall hall = hallRepository.findById(id).orElseGet(null);
        if(hall != null && (hall.getClinic() == null))
        {
           hallRepository.deleteById(id);
           return true;
        }else
            return false;


    }

    public HallDTO update(HallDTO hallDTO)
    {
        Hall hall = hallRepository.findById(hallDTO.getId()).orElseGet(null);
        if(hall != null && hall.getClinic()==null) {
            if(!hallDTO.getName().equals("") && !hallDTO.getNumber().equals("")) {
                hall.setName(hallDTO.getName());
                hall.setNumber(hallDTO.getNumber());
                hall.setId(hallDTO.getId());
            }
        }
        return new HallDTO( hallRepository.save(hall));
    }

    public HallDTO save(HallDTO hallDTO){
        Hall hall = new Hall();
        hall.setName(hallDTO.getName());
        hall.setNumber(hallDTO.getNumber());

        return new HallDTO( hallRepository.save(hall));
    }
}
