package team58.healthy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team58.healthy.model.ClinicAdmin;

public interface ClinicAdminRepository extends JpaRepository<ClinicAdmin, Long> {
    ClinicAdmin findByEmail(String email);
}
