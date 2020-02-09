package team58.healthy.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import team58.healthy.constants.ClinicConstants;
import team58.healthy.model.Clinic;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class ClinicRepositoryTest {
    @Autowired
    private ClinicRepository clinicRepository;

    @Test
    public void testFindAll() {
        List<Clinic> clinics = clinicRepository.findAll();
        assertThat(clinics).hasSize(ClinicConstants.COUNT_1);
    }

    @Test
    public void testFindAllWithCheckupTypeId() {
        List<Clinic> clinics = clinicRepository.findAllWithCheckupTypeId(ClinicConstants.CHK_T_ID_2);
        assertThat(clinics).hasSize(ClinicConstants.COUNT_2);

        assertEquals(clinics.get(0).getId(), ClinicConstants.CLINIC_ID_2);
    }
}
