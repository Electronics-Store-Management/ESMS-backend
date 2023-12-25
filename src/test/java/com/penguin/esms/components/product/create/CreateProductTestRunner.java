package com.penguin.esms.components.product.create;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.penguin.esms.EsmsApplication;
import com.penguin.esms.components.authentication.responses.AuthenticationResponse;
import com.penguin.esms.components.customer.CustomerEntity;
import com.penguin.esms.components.customer.CustomerRepo;
import com.penguin.esms.components.product.ProductEntity;
import com.penguin.esms.components.product.ProductRepo;
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
class CreateProductTestRunner {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TestService testService;
    private ProductEntity product;

    private AuthenticationResponse authenticationResponse;

    @Autowired
    public CreateProductTestRunner(MockMvc mockMvc, ObjectMapper objectMapper, ProductRepo productRepo, StaffRepository staffRepository, TestService testService) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.productRepo = productRepo;
        this.staffRepository = staffRepository;
        this.testService = testService;
    }

    public static List<TestCase> testData() throws IOException {
        return TestUtils.readTestDataFromCsv("src\\test\\java\\com\\penguin\\esms\\components\\product\\create\\test-cases.csv", new ArrayList<>(List.of("name", "unit", "price", "quantity","photoURL")), new ArrayList<>(List.of("status")));
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void shouldCreateProduct(TestCase testCase) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/product")
                        .param("name", testCase.getInput().get("name").equals("") ? null : testCase.getInput().get("name"))
                        .param("unit", testCase.getInput().get("unit"))
                        .param("quantity", testCase.getInput().get("quantity"))
                        .param("price", testCase.getInput().get("price"))
                        .param("photoURL", testCase.getInput().get("photoURL"))
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                        .header("Authorization", "Bearer " + authenticationResponse.getAccessToken())
                )
                .andExpect(status().is(Integer.parseInt(testCase.getExpected().get("status"))))
                .andDo(print()).andReturn();
    }

    @BeforeAll
    public void setup() throws Exception {
        CompletableFuture<Void> setupEntity = CompletableFuture.runAsync(() -> {
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
        staffRepository.deleteById(this.authenticationResponse.getId());
    }
}