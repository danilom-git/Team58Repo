package team58.healthy.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import team58.healthy.constants.DoctorConstants;
import team58.healthy.dto.DoctorWithAvailableTimeDTO;
import team58.healthy.service.DoctorService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class DoctorServiceTest {
    @Autowired
    private DoctorService doctorService;

    @Test
    public void getAllInClinicWithCheckupTypeOnDate() {
        List<DoctorWithAvailableTimeDTO> doctors = doctorService.getAllInClinicWithCheckupTypeOnDate(DoctorConstants.CLINIC_ID_2,
                DoctorConstants.CHK_T_2, DoctorConstants.YEAR_2, DoctorConstants.MONTH_2, DoctorConstants.DAY_2);
        assertThat(doctors).hasSize(DoctorConstants.COUNT_2);
    }
}
