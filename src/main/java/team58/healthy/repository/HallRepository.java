package team58.healthy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import team58.healthy.model.Hall;

import java.util.Date;
import java.util.List;

public interface HallRepository extends JpaRepository<Hall, Long> {
    Page<Hall> findAll(Pageable pageable);
    List<Hall> findAllByClinicId(Long id);

    @Query(nativeQuery =  true,value = "select distinct h.id,h.name,h.number,h.clinic_id from hall h join checkup c on h.id = c.hall_id where (date(c.start_date) = date(?1)) and (h.number like %?3%) and (h.name like %?2%)")
    List<Hall> getHallsOnDateAndName(Date date, String name, String number);
}
