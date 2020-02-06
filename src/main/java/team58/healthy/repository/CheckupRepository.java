package team58.healthy.repository;

import org.hibernate.annotations.Check;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import team58.healthy.model.Checkup;
import team58.healthy.model.Patient;

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

    @Query(nativeQuery =  true,value = "select * from checkup c where  (NOT((c.start_date <= ?1 and c.end_date <= ?1) or ( c.start_date >= ?2 and c.end_date >= ?2)) and (date(c.start_date) = date(?1)) and ((c.doctor_id = ?3) or (c.hall_id = ?4))) ")
    List<Checkup> findAllByDateAndDoctor (Date startTime,Date endTime,Long doctorId,Long hallId);

    @Query(nativeQuery = true,
        value = "select * from checkup where patient_id = ?1 order by start_date asc")
    List<Checkup> findByPatientId(Long patientId);

    @Query(nativeQuery = true,
        value = "select * from checkup where patient_id = ?1 and checkup_type_id = ?2 order by start_date asc")
    List<Checkup> findByPatientIdAndTypeId(Long patientId, Long checkupTypeId);

    @Query(nativeQuery = true,value = "select * from checkup where (hall_id = ?1) and (date(start_date) = date(?2))")
    List<Checkup> findAllByHallAndDate (Long hallId,Date date);

    @Query(nativeQuery = true,value = "select * from checkup where (hall_id = ?1) and (date(start_date) = date(?2)) and (end_date > ?2) ORDER BY end_date ASC")
    List<Checkup> findAllByHallAndEndDate (Long hallId,Date endDate);

    @Query(nativeQuery =  true,value = "select * from checkup c where  (NOT((c.start_date <= ?1 and c.end_date <= ?1) or ( c.start_date >= ?2 and c.end_date >= ?2)) and (date(c.start_date) = date(?1)) and (c.hall_id = ?3)) ")
    List<Checkup> findAllForCheckupSchedule (Date startTime,Date endTime,Long hallId);

    @Query(nativeQuery =  true,value = "select * from checkup c where  (NOT((c.start_date <= ?1 and c.end_date <= ?1) or ( c.start_date >= ?2 and c.end_date >= ?2)) and (date(c.start_date) = date(?1)) and (c.hall_id = ?3)) ")
    List<Checkup> findAllByDateAndHall (Date startTime,Date endTime,Long hallId);
}
