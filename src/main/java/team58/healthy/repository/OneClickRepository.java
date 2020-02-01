package team58.healthy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import team58.healthy.model.OneClick;

import java.util.List;

public interface OneClickRepository extends JpaRepository<OneClick,Long> {

    Page<OneClick> findAll(Pageable pageable);

    List<OneClick> findAllByClinicId(Long id);
}