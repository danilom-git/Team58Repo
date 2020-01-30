package team58.healthy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team58.healthy.model.ClinicCenterAdmin;

public interface ClinicCenterAdminRepository extends JpaRepository<ClinicCenterAdmin, Long> {
    ClinicCenterAdmin findByEmail(String email);
}
