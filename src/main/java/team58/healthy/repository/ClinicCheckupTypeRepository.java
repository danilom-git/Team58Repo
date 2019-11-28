package team58.healthy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team58.healthy.model.CheckupType;
import team58.healthy.model.Clinic;
import team58.healthy.model.ClinicCheckupType;

public interface ClinicCheckupTypeRepository extends JpaRepository<ClinicCheckupType, Long> {

    ClinicCheckupType findByClinicAndCheckupTypeId(Clinic clinic, Long checkupTypeId);
}
