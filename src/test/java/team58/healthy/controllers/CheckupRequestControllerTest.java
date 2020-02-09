package team58.healthy.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.nio.charset.Charset;

import javax.annotation.PostConstruct;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import team58.healthy.Utils;
import team58.healthy.constants.CheckupRequestConstants;
import team58.healthy.constants.ClinicConstants;
import team58.healthy.constants.DoctorConstants;
import team58.healthy.constants.OneClickConstants;
import team58.healthy.dto.CheckupRequestDTO;
import team58.healthy.dto.DoctorDTO;
import team58.healthy.dto.TokenDTO;
import team58.healthy.security.auth.JwtAuthenticationRequest;

import javax.annotation.PostConstruct;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class CheckupRequestControllerTest {
    private static final String URL_PREFIX = "/api/checkupRequests/";

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);

    private MockMvc mockMvc;

    private String token;

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Before
    public void login() {
        ResponseEntity<TokenDTO> responseEntity = restTemplate.postForEntity("/auth/login",
                new JwtAuthenticationRequest("patient01@somemail.com", "123"), TokenDTO.class);
        token = "Bearer " + responseEntity.getBody().getToken();
    }

    @PostConstruct
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testPost() throws Exception {
        final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                "patient01@somemail.com", "123"));

        SecurityContextHolder.getContext().setAuthentication(authentication);

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

        String body = Utils.json(input);

        mockMvc.perform(post(URL_PREFIX).header("Authorization", token).contentType(contentType).content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clinicId").value(CheckupRequestConstants.CLINIC_ID_1));
    }
}
