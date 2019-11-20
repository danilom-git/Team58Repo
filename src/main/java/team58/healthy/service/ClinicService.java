package team58.healthy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import team58.healthy.model.Clinic;
import team58.healthy.repository.ClinicRepository;

import java.util.List;

@Service
public class ClinicService {

    @Autowired
    private ClinicRepository clinicRepository;

    public List<Clinic> findAll() { return clinicRepository.findAll(); }

    public Page<Clinic> findAll(Pageable pageable) { return clinicRepository.findAll(pageable); }
}
