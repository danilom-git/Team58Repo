package team58.healthy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team58.healthy.model.ClinicAdmin;
import team58.healthy.model.Doctor;

public interface ClinicAdminRepository extends JpaRepository<ClinicAdmin,Long> {

    ClinicAdmin findByEmail(String email);
}
