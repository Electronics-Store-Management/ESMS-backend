package com.penguin.esms.components.supplier.viewByPhone;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.penguin.esms.EsmsApplication;
import com.penguin.esms.components.authentication.responses.AuthenticationResponse;
import com.penguin.esms.components.customer.CustomerEntity;
import com.penguin.esms.components.customer.CustomerRepo;
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
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
class ViewCustomerByPhoneTestRunner {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TestService testService;

    private AuthenticationResponse authenticationResponse;
    private CustomerEntity customer;


    @Autowired
    public ViewCustomerByPhoneTestRunner(MockMvc mockMvc, ObjectMapper objectMapper, CustomerRepo customerRepo, StaffRepository staffRepository, TestService testService) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.customerRepo = customerRepo;
        this.staffRepository = staffRepository;
        this.testService = testService;
    }

    public static List<TestCase> testData() throws IOException {
        return TestUtils.readTestDataFromCsv("src\\test\\java\\com\\penguin\\esms\\components\\customer\\viewByPhone\\test-cases.csv", new ArrayList<>(List.of("phone")), new ArrayList<>(List.of("status")));
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void shouldViewCustomer(TestCase testCase) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/customer/phone?phone=" + testCase.getInput().get("phone"))
                        .header("Authorization", "Bearer " + authenticationResponse.getAccessToken())
                )
                .andExpect(status().is(Integer.parseInt(testCase.getExpected().get("status"))))
                .andDo(print()).andReturn();
    }

    @BeforeAll
    public void setup() throws Exception {
        CustomerEntity cutomerr = new CustomerEntity();
        cutomerr.setPhone("0971241207");

        CompletableFuture<Void> setupEntity = CompletableFuture.runAsync(() -> {
            this.customer = customerRepo.save(cutomerr);
            try {
                authenticationResponse = testService.getAuthenticationInfo();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        setupEntity.join();
    }

    @AfterAll
    public void cleanup() {
        customerRepo.deleteById(this.customer.getId());
        staffRepository.deleteById(this.authenticationResponse.getId());
    }
}