package com.penguin.esms.components.staff.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.penguin.esms.EsmsApplication;
import com.penguin.esms.components.authentication.responses.AuthenticationResponse;
import com.penguin.esms.components.staff.StaffRepository;
import com.penguin.esms.components.supplier.SupplierEntity;
import com.penguin.esms.components.supplier.SupplierRepo;
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
class ViewSupplierTestRunner {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private SupplierRepo supplierRepo;

    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TestService testService;

    private AuthenticationResponse authenticationResponse;
    private SupplierEntity supplier;


    @Autowired
    public ViewSupplierTestRunner(MockMvc mockMvc, ObjectMapper objectMapper, SupplierRepo supplierRepo, StaffRepository staffRepository, TestService testService) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.supplierRepo = supplierRepo;
        this.staffRepository = staffRepository;
        this.testService = testService;
    }

    public static List<TestCase> testData() throws IOException {
        return TestUtils.readTestDataFromCsv("src\\test\\java\\com\\penguin\\esms\\components\\supplier\\view\\test-cases.csv", new ArrayList<>(List.of("name")), new ArrayList<>(List.of("length", "status")));
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void shouldViewCustomer(TestCase testCase) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/supplier?name=" + testCase.getInput().get("name"))
                        .header("Authorization", "Bearer " + authenticationResponse.getAccessToken())
                )
                .andExpect(status().is(Integer.parseInt(testCase.getExpected().get("status"))))
                .andExpect(jsonPath("$").isArray())
                .andDo(print()).andReturn();
    }

    @BeforeAll
    public void setup() throws Exception {
        SupplierEntity entity = new SupplierEntity();
        entity.setName("This is a supplier");

        CompletableFuture<Void> setupEntity = CompletableFuture.runAsync(() -> {
            this.supplier = supplierRepo.save(entity);
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
        supplierRepo.deleteById(this.supplier.getId());
        staffRepository.deleteById(this.authenticationResponse.getId());
    }
}