package team58.healthy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team58.healthy.model.ClinicCenterAdmin;
import team58.healthy.repository.ClinicCenterAdminRepository;

@Service
public class ClinicCenterAdminService {

    @Autowired
    private ClinicCenterAdminRepository clinicCenterAdminRepository;

    public ClinicCenterAdmin findByEmail(String email) { return clinicCenterAdminRepository.findByEmail(email); }
}
