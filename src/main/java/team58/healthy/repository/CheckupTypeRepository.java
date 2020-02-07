package team58.healthy.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import team58.healthy.model.CheckupType;

import java.util.List;

public interface CheckupTypeRepository extends JpaRepository<CheckupType, Long> {

    @Query(nativeQuery = true,value = "select * from checkup_type ct join clinic_checkup_type cct on ct.id = cct.checkup_type_id where cct.clinic_id = ?1")
    List<CheckupType> findAllByClinicId(Long id);

    @Query(nativeQuery = true,value = "select distinct * from clinic_checkup_type cct join checkup_type ctt on ctt.id = cct.checkup_type_id where cct.checkup_type_id not in (select ct.checkup_type_id from clinic_checkup_type ct where ct.clinic_id = ?1)")
    List<CheckupType> findAllByClinicIdFalse(Long id);

    @Query(nativeQuery = true,value = "select price from clinic_checkup_type  where (checkup_type_id = ?1) and (clinic_id = ?2)")
    double findPriceOfCheckup(Long id,Long clinic_id);

    @Query(nativeQuery = true,value = "select cct.checkup_type_id from clinic_checkup_type cct where (cct.id = ?1)")
    Long findIdOfCheckup(Long id);
}
