package team58.healthy;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import team58.healthy.controllers.CheckupRequestControllerTest;
import team58.healthy.controllers.ClinicControllerTest;
import team58.healthy.controllers.DoctorControllerTest;
import team58.healthy.controllers.OneClickControllerTest;
import team58.healthy.repository.ClinicRepositoryTest;
import team58.healthy.repository.DoctorRepositoryTest;
import team58.healthy.repository.OneClickRepositoryTest;
import team58.healthy.services.CheckupRequestServiceTest;
import team58.healthy.services.ClinicServiceTest;
import team58.healthy.services.DoctorServiceTest;
import team58.healthy.services.OneClickServiceTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ClinicRepositoryTest.class,
        DoctorRepositoryTest.class,
        OneClickRepositoryTest.class,
        CheckupRequestServiceTest.class,
        ClinicServiceTest.class,
        DoctorServiceTest.class,
        OneClickServiceTest.class,
        CheckupRequestControllerTest.class,
        ClinicControllerTest.class,
        DoctorControllerTest.class,
        OneClickControllerTest.class
})
public class MultiTest {}
