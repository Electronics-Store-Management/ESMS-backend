package com.penguin.esms.components.authentication.signin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.penguin.esms.EsmsApplication;
import com.penguin.esms.components.authentication.requests.AuthenticationRequest;
import com.penguin.esms.components.authentication.responses.AuthenticationResponse;
import com.penguin.esms.components.category.CategoryRepo;
import com.penguin.esms.components.staff.Role;
import com.penguin.esms.components.staff.StaffEntity;
import com.penguin.esms.components.staff.StaffRepository;
import com.penguin.esms.utils.TestCase;
import com.penguin.esms.utils.TestService;
import com.penguin.esms.utils.TestUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@DirtiesContext
@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = EsmsApplication.class)
@TestPropertySource(
        locations = "classpath:application-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SignInTestRunner {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private TestService testService;
    private PasswordEncoder passwordEncoder;

    private AuthenticationResponse authenticationResponse;

    @Autowired
    public SignInTestRunner(MockMvc mockMvc, ObjectMapper objectMapper, PasswordEncoder passwordEncoder, CategoryRepo categoryRepo, StaffRepository staffRepository, TestService testService) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.categoryRepo = categoryRepo;
        this.staffRepository = staffRepository;
        this.testService = testService;
        this.passwordEncoder = passwordEncoder;
    }

    public static List<TestCase> testData() throws IOException {
        return TestUtils.readTestDataFromCsv("src\\test\\java\\com\\penguin\\esms\\components\\authentication\\signin\\test-cases.csv", new ArrayList<>(List.of("email", "password")), new ArrayList<>(List.of("status")));
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void shouldSignIn(TestCase testCase) throws Exception {
        AuthenticationRequest request = new AuthenticationRequest();
        request.setEmail(testCase.getInput().get("email"));
        request.setPassword(testCase.getInput().get("password"));
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/authenticate")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().is(Integer.parseInt(testCase.getExpected().get("status"))))
                .andDo(print()).andReturn();
    }

    @BeforeAll
    public void setup() throws Exception {
        CompletableFuture<Void> setupEntity = CompletableFuture.runAsync(() -> {
            try {
                StaffEntity staff = new StaffEntity();
                staff.setCitizenId("000000000000");
                staff.setPhone("0000000000");
                staff.setPassword(passwordEncoder.encode("123456"));
                staff.setName("Demo name for the staff");
                staff.setEmail("hoanghyyy@gmail.com");
                staff.setRole(Role.ADMIN);

                authenticationResponse = new AuthenticationResponse();
                authenticationResponse.setId(staffRepository.save(staff).getId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        setupEntity.join();
}

    @AfterAll
    public void cleanup() {
        staffRepository.deleteById(this.authenticationResponse.getId());
    }
}