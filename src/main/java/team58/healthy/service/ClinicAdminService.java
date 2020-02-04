package team58.healthy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team58.healthy.dto.ClinicAdminDTO;
import team58.healthy.dto.DoctorDTO;
import team58.healthy.model.ClinicAdmin;
import team58.healthy.model.Doctor;
import team58.healthy.repository.ClinicAdminRepository;
import team58.healthy.security.TokenUtils;

@Service
public class ClinicAdminService {
    @Autowired
    private ClinicAdminRepository clinicAdminRepository;

    @Autowired
    TokenUtils tokenUtils;

    public ClinicAdmin findByEmail(String email) { return clinicAdminRepository.findByEmail(email); }

    public ClinicAdmin save(ClinicAdmin clinicAdmin) { return clinicAdminRepository.save(clinicAdmin); }


    public ClinicAdminDTO findOne(Long id){
        return new ClinicAdminDTO(clinicAdminRepository.findById(id).orElseGet(null));
    }

    public ClinicAdminDTO findOneByToken(String token){
        String username = tokenUtils.getUsernameFromToken(token);
        ClinicAdmin ca = this.findByEmail(username);
        return new ClinicAdminDTO(ca);
    }

    public ClinicAdminDTO getFromToken(String token) {
        String email = tokenUtils.getUsernameFromToken(token.substring(7));
        ClinicAdmin clinicAdmin = findByEmail(email);
        return new ClinicAdminDTO(clinicAdmin);
    }
}
