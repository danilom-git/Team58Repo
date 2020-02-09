package team58.healthy.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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
import team58.healthy.constants.ClinicConstants;
import team58.healthy.constants.DoctorConstants;
import team58.healthy.dto.DoctorDTO;
import team58.healthy.dto.TokenDTO;
import team58.healthy.security.auth.JwtAuthenticationRequest;

import javax.annotation.PostConstruct;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class DoctorControllerTest {
    private static final String URL_PREFIX = "/api/doctors/";

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
    public void testGetAllInClinicWithCheckupType() throws Exception {
        final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                "patient01@somemail.com", "123"));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String url = URL_PREFIX + "clinic:" + DoctorConstants.CLINIC_ID_1 + "/checkupType:" + DoctorConstants.CHK_T_1;
        mockMvc.perform(get(url).header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(DoctorConstants.COUNT_1)))
                .andExpect(jsonPath("$.[0].id").value(DoctorConstants.DOCTOR_ID_1));
    }
}
