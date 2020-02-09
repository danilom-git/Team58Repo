package team58.healthy.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import team58.healthy.constants.OneClickConstants;
import team58.healthy.dto.OneClickDTOPretty;
import team58.healthy.service.OneClickService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class OneClickServiceTest {
    @Autowired
    private OneClickService oneClickService;

    @Test
    public void getPrettyByClinicByCheckupType() {
        List<OneClickDTOPretty> oneClicks = oneClickService.getPrettyByClinicByCheckupType(OneClickConstants.CLINIC_ID_4, OneClickConstants.CHK_T_ID_4);
        assertThat(oneClicks).hasSize(OneClickConstants.COUNT_4);
        OneClickDTOPretty oneClickByDoc = null;
        for (OneClickDTOPretty oneClick : oneClicks) {
            if (oneClick.getDoctorFullName().equals(OneClickConstants.DOCTOR_ID_FULL_NAME)) {
                oneClickByDoc = oneClick;
                break;
            }
        }
        assertNotNull(oneClickByDoc);
    }

}
