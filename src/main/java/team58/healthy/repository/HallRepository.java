package team58.healthy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import team58.healthy.model.Hall;

public interface HallRepository extends JpaRepository<Hall, Long> {
    Page<Hall> findAll(Pageable pageable);
}
