package team58.healthy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team58.healthy.model.CheckupRequest;

import java.util.List;

public interface CheckupRequestRepository extends JpaRepository<CheckupRequest, Long> {
    List<CheckupRequest> findAllByClinicId(Long id);
}
