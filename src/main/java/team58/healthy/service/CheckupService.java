package team58.healthy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team58.healthy.model.Checkup;
import team58.healthy.repository.CheckupRepository;

import java.util.Date;
import java.util.List;

@Service
public class CheckupService {

    @Autowired
    private CheckupRepository checkupRepository;

    public List<Checkup> findAll() { return checkupRepository.findAll(); }

    public List<Checkup> findAllOnDate(Date date) { return checkupRepository.findAllOnDate(date); }

    public List<Checkup> findAllOnDateByDoctor(Date date, Long doctorId) {
        return checkupRepository.findAllOnDateByDoctor(date, doctorId);
    }
}