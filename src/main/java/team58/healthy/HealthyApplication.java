package team58.healthy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import team58.healthy.service.DoctorService;

@SpringBootApplication
public class HealthyApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthyApplication.class, args);
	}

}
