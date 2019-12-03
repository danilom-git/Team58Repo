package team58.healthy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import team58.healthy.model.Clinic;
import team58.healthy.model.Doctor;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    List<Doctor> findAllByName(String Name);

    List<Doctor> findAllByClinic(Clinic clinic);

    List<Doctor> findAllByClinicId(Long clinicId);

    @Query (nativeQuery = true,
        value = "select * from doctor d join doctor_checkup_types dct on d.id = dct.doctor_id where d.clinic_id = ?1 and dct.checkup_types_id = ?2")
    List<Doctor> findAllByClinicAndCheckupType(Long clinicId, Long checkupTypeId);


}
