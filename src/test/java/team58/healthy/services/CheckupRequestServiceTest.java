package team58.healthy.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import team58.healthy.constants.CheckupRequestConstants;
import team58.healthy.constants.OneClickConstants;
import team58.healthy.dto.CheckupRequestDTO;
import team58.healthy.dto.OneClickDTOPretty;
import team58.healthy.model.CheckupType;
import team58.healthy.model.Clinic;
import team58.healthy.model.Doctor;
import team58.healthy.model.Patient;
import team58.healthy.repository.CheckupTypeRepository;
import team58.healthy.repository.ClinicRepository;
import team58.healthy.repository.DoctorRepository;
import team58.healthy.repository.PatientRepository;
import team58.healthy.service.CheckupRequestService;
import team58.healthy.service.OneClickService;
import team58.healthy.service.PatientService;

import javax.persistence.EntityManager;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class CheckupRequestServiceTest {
    @Autowired
    private CheckupRequestService checkupRequestService;

    @Test
    public void testSave() {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(CheckupRequestConstants.YEAR_START_1, CheckupRequestConstants.MONTH_START_1 - 1, CheckupRequestConstants.DAY_START_1,
                CheckupRequestConstants.HOUR_START_1, CheckupRequestConstants.MINUTE_START_1);
        Date startDate = cal.getTime();

        cal.set(CheckupRequestConstants.YEAR_START_1, CheckupRequestConstants.MONTH_START_1 - 1, CheckupRequestConstants.DAY_START_1,
                CheckupRequestConstants.HOUR_END_1, CheckupRequestConstants.MINUTE_END_1);
        Date endDate = cal.getTime();

        CheckupRequestDTO input = new CheckupRequestDTO(startDate, endDate, CheckupRequestConstants.DOCTOR_ID_1,
                CheckupRequestConstants.CHK_T_ID_1, CheckupRequestConstants.CLINIC_ID_1, CheckupRequestConstants.PATIENT_ID);

        CheckupRequestDTO retVal = checkupRequestService.save(input);
        assertNotNull(retVal);
    }
}
