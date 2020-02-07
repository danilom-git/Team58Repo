package team58.healthy.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import team58.healthy.model.CheckupType;

import java.util.List;

public interface CheckupTypeRepository extends JpaRepository<CheckupType, Long> {

    @Query(nativeQuery = true,value = "select * from checkup_type ct join clinic_checkup_type cct on ct.id = cct.checkup_type_id where cct.clinic_id = ?1")
    List<CheckupType> findAllByClinicId(Long id);

    @Query(nativeQuery = true,value = "select cct.price from clinic_checkup_type cct where cct.checkup_type_id = ?1")
    double findPriceOfCheckup(Long id);
}
