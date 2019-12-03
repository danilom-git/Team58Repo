package team58.healthy.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import team58.healthy.model.CheckupType;

public interface CheckupTypeRepository extends JpaRepository<CheckupType, Long> {
}
