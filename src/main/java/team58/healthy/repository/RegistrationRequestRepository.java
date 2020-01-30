package team58.healthy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team58.healthy.model.RegistrationRequest;

public interface RegistrationRequestRepository extends JpaRepository<RegistrationRequest, Long> {

}
