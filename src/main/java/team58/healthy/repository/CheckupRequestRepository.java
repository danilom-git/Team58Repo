package team58.healthy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import team58.healthy.model.CheckupRequest;

import java.util.List;

public interface CheckupRequestRepository extends JpaRepository<CheckupRequest, Long> {
    List<CheckupRequest> findAllByClinicId(Long id);

    @Query(nativeQuery =  true,value = "select * from checkup_request c  where (c.id = ?1) and (c.on_wait = false)")
    List<CheckupRequest> findAllByClinicIdAndOnWait(Long id);

}
