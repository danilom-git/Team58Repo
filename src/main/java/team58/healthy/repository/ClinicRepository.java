package team58.healthy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import team58.healthy.model.Clinic;

import java.util.List;

public interface ClinicRepository extends JpaRepository<Clinic, Long> {

    Page<Clinic> findAll(Pageable pageable);

    @Query(nativeQuery = true,
            value = "select distinct c.id, c.name, c.country, c.city, c.address, c.x_coord, c.y_coord from clinic c join doctor d on c.id = d.clinic_id join doctor_checkup_types dct on d.id = dct.doctor_id where dct.checkup_types_id = ?1")
    List<Clinic> findAllWithCheckupTypeId(Long checkupTypeId);
}
