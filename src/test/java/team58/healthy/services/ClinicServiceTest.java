package team58.healthy.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import team58.healthy.constants.ClinicConstants;
import team58.healthy.dto.ClinicWithCheckupDTO;
import team58.healthy.service.ClinicService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class ClinicServiceTest {
    @Autowired
    private ClinicService clinicService;

    @Test
    public void testGetAllClinicsWithCheckupTypeOnDate() {
        List<ClinicWithCheckupDTO> clinics = clinicService.getAllClinicsWithCheckupTypeOnDate(ClinicConstants.CHK_T_ID_3,
                ClinicConstants.YEAR_3, ClinicConstants.MONTH_3, ClinicConstants.DAY_3);
        assertThat(clinics).hasSize(ClinicConstants.COUNT_3);
        assertEquals(clinics.get(0).getId(), ClinicConstants.CLINIC_ID_3);
    }

    @Test
    public void getAllClinicsWithCheckupType() {
        List<ClinicWithCheckupDTO> clinics = clinicService.getAllClinicsWithCheckupType(ClinicConstants.CHK_T_ID_3);
        assertThat(clinics).hasSize(ClinicConstants.COUNT_3);
        assertEquals(clinics.get(0).getId(), ClinicConstants.CLINIC_ID_3);
    }
}
