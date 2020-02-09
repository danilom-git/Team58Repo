package team58.healthy.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import team58.healthy.constants.DoctorConstants;
import team58.healthy.model.Doctor;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository doctorRepository;

    @Test
    public void testFindAllByClinicAndCheckupType() {
        List<Doctor> doctors = doctorRepository.findAllByClinicAndCheckupType(DoctorConstants.CLINIC_ID_1, DoctorConstants.CHK_T_1);
        assertThat(doctors).hasSize(DoctorConstants.COUNT_1);

        assertEquals(doctors.get(0).getId(), DoctorConstants.DOCTOR_ID_1);
    }

}
