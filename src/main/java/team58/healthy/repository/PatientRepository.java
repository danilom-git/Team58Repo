package team58.healthy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import team58.healthy.dto.PatientDTO;
import team58.healthy.model.Doctor;
import team58.healthy.model.Patient;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Long> {

    List<Patient> findAllByName(String name);
    List<Patient> findAllByLastName(String lastName);
    List<Patient> findAllByHealthInsuranceId(String healthInsuranceId);
    List<Patient> findByNameAndLastNameAllIgnoringCase(String firstName, String lastName);
    List<Patient> findByNameAndHealthInsuranceIdAllIgnoringCase(String firstName, String healthInsuranceId);
    List<Patient> findByLastNameAndHealthInsuranceIdAllIgnoringCase(String lastName, String HealthInsuranceId);
    List<Patient> findByNameAndLastNameAndHealthInsuranceIdAllIgnoringCase(String firstName, String lastName,String healthInsuranceId);

    @Query(nativeQuery = true,
            value = "select * from patient p where (p.name like %?1%) and (p.last_name like %?2%) and (p.health_insurance_id like %?3%) ")
    List<Patient> searchAll(String name, String lastName,String healthInsuranceId);
}
