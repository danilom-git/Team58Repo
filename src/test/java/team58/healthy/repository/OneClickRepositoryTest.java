package team58.healthy.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import team58.healthy.constants.DoctorConstants;
import team58.healthy.constants.OneClickConstants;
import team58.healthy.model.Doctor;
import team58.healthy.model.OneClick;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class OneClickRepositoryTest {

    @Autowired
    private OneClickRepository oneClickRepository;

    @Test
    public void testFindAll() {
        List<OneClick> oneClicks = oneClickRepository.findAll();
        assertThat(oneClicks).hasSize(OneClickConstants.COUNT_1);
    }

    @Test
    public void testGetByCheckupType() {
        List<OneClick> oneClicks = oneClickRepository.getByCheckupType(OneClickConstants.CHK_T_ID_2);
        assertThat(oneClicks).hasSize(OneClickConstants.COUNT_2);
        assertEquals(oneClicks.get(0).getClinic().getId(), OneClickConstants.CLINIC_ID_2);
    }

    @Test
    public void testGetByClinicByCheckupType() {
        List<OneClick> oneClicks = oneClickRepository.getByClinicByCheckupType(OneClickConstants.CLINIC_ID_3, OneClickConstants.CHK_T_ID_3);
        assertThat(oneClicks).hasSize(OneClickConstants.COUNT_3);
    }
}
