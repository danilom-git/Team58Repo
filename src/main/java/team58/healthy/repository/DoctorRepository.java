package team58.healthy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team58.healthy.model.Doctor;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    List<Doctor> findAllByIme(String ime);
}
