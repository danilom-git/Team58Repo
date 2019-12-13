package team58.healthy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team58.healthy.dto.ClinicAdminDTO;
import team58.healthy.model.ClinicAdmin;
import team58.healthy.repository.ClinicAdminRepository;

@Service
public class ClinicAdminService {

    @Autowired
    private ClinicAdminRepository clinicAdminRepository;

    public ClinicAdminDTO findOne(Long id){
        return new ClinicAdminDTO(clinicAdminRepository.findById(id).orElseGet(null));
    }

}
