package team58.healthy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team58.healthy.model.CheckupType;
import team58.healthy.repository.CheckupTypeRepository;

import java.util.List;

@Service
public class CheckupTypeService {

    @Autowired
    private CheckupTypeRepository checkupTypeRepository;

    public List<CheckupType> findAll() { return checkupTypeRepository.findAll(); }

    public CheckupType findById(Long id) { return checkupTypeRepository.findById(id).orElseGet(null); }
}
