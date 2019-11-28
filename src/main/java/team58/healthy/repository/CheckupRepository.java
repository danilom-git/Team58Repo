package team58.healthy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import team58.healthy.model.Checkup;

import java.util.Date;
import java.util.List;

public interface CheckupRepository extends JpaRepository<Checkup, Long> {

    @Query(nativeQuery = true,
        value = "select * from checkup where date(start_date) = date(?1)")
    List<Checkup> findAllOnDate(Date date);
}
