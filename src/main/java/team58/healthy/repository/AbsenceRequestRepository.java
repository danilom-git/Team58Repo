package team58.healthy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team58.healthy.model.AbsenceRequest;
import team58.healthy.model.Checkup;

public interface AbsenceRequestRepository extends JpaRepository<AbsenceRequest, Long> {

}
