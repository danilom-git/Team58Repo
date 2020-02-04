package team58.healthy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team58.healthy.model.AbsenceRequest;

import java.util.List;

public interface AbsenceRequestRepository extends JpaRepository<AbsenceRequest, Long> {
    List<AbsenceRequest> findAllByClinicIdAndAnswered(Long id,Boolean answered);
}
