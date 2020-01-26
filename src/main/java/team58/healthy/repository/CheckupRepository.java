package team58.healthy.repository;

import org.hibernate.annotations.Check;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import team58.healthy.model.Checkup;

import java.util.Date;
import java.util.List;

public interface CheckupRepository extends JpaRepository<Checkup, Long> {

    @Query(nativeQuery = true,
        value = "select * from checkup where date(start_date) = date(?1)")
    List<Checkup> findAllOnDate(Date date);

    @Query(nativeQuery = true,
        value = "select * from checkup c where date(c.start_date) = date(?1) and c.doctor_id = ?2")
    List<Checkup> findAllOnDateByDoctor(Date date, Long doctorId);

    @Query(nativeQuery = true,value = "select * from checkup where hall_id = ?1")
    List<Checkup> findAllByHall (Long hallId);
}
