package team58.healthy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import team58.healthy.model.OneClick;

import java.util.Date;
import java.util.List;

public interface OneClickRepository extends JpaRepository<OneClick,Long> {

    Page<OneClick> findAll(Pageable pageable);

    List<OneClick> findAllByClinicId(Long id);


    @Query (nativeQuery = true,
        value = "select * from one_click where checkup_type_id = ?1")
    List<OneClick> getByCheckupType(Long checkupTypeId);

    @Query (nativeQuery = true,
            value = "select * from one_click where date(start_time) = date(?1)")
    List<OneClick> getOnDate(Date date);

    @Query (nativeQuery = true,
            value = "select * from one_click where checkup_type_id = ?1 and date(start_time) = date(?2)")
    List<OneClick> getByCheckupTypeOnDate(Long checkupTypeId, Date date);

    @Query (nativeQuery = true,
            value = "select * from one_click where clinic_id = ?1 and checkup_type_id = ?2")
    List<OneClick> getByClinicByCheckupType(Long clinicId, Long checkupTypeId);

    @Query (nativeQuery = true,
            value = "select * from one_click where clinic_id = ?1 and date(start_time) = date(?2)")
    List<OneClick> getByClinicOnDate(Long clinicId, Date date);

    @Query (nativeQuery = true,
            value = "select * from one_click where clinic_id = ?1 and checkup_type_id = ?2 and date(start_time) = date(?3)")
    List<OneClick> getByClinicByCheckupTypeOnDate(Long clinicId, Long checkupTypeId, Date date);
}
