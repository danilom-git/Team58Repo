package team58.healthy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team58.healthy.model.Hall;
import team58.healthy.repository.HallRepository;

import java.util.List;

@Service
public class HallService {
    @Autowired
    private HallRepository hallRepository;

    public Hall findOne(Long id){return hallRepository.findById(id).orElseGet(null);}

    public List<Hall> findAll(){return hallRepository.findAll();}

    public void remove(Long id){ hallRepository.deleteById(id);}

    public Hall save(Hall hall){return hallRepository.save(hall);}
}
