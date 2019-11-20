package team58.healthy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import team58.healthy.model.Clinic;

public interface ClinicRepository extends JpaRepository<Clinic, Long> {

    Page<Clinic> findAll(Pageable pageable);

}
