package com.penguin.esms.components.importBill.getAll;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.penguin.esms.EsmsApplication;
import com.penguin.esms.components.authentication.responses.AuthenticationResponse;
import com.penguin.esms.components.customer.CustomerRepo;
import com.penguin.esms.components.importBill.ImportBillEntity;
import com.penguin.esms.components.product.ProductEntity;
import com.penguin.esms.components.product.ProductRepo;
import com.penguin.esms.components.staff.StaffRepository;
import com.penguin.esms.utils.TestService;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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
class GetAllImportBillTestRunner {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TestService testService;
    private ProductEntity product;
    private ImportBillEntity importBillEntity;

    private AuthenticationResponse authenticationResponse;

    @Autowired
    public GetAllImportBillTestRunner(MockMvc mockMvc, ObjectMapper objectMapper, CustomerRepo customerRepo, StaffRepository staffRepository, ProductRepo productRepo, TestService testService) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.customerRepo = customerRepo;
        this.staffRepository = staffRepository;
        this.testService = testService;
        this.productRepo = productRepo;
    }

    @Test
        public void shouldGetAllImportBill() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/import")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .header("Authorization", "Bearer " + authenticationResponse.getAccessToken())
                )
                .andExpect(status().is(200))
                .andExpect(jsonPath("$").isArray())
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

        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            try {
                ProductEntity productEntity = new ProductEntity();
                productEntity.setName(testService.generateRandomString("afdsaf", 10));

                product = productRepo.save(productEntity);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        completableFuture.join();

        mockMvc.perform(MockMvcRequestBuilders.post("/import")
                        .content(String.format("""
                                {
                                    "paymentMethod": "lfdasfsadt",
                                    "importProducts": [
                                        {
                                            "productId": "%s",
                                            "quantity": 100,
                                            "price": 3000
                                        }
                                    ]
                                }
                                """, product.getId()))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .header("Authorization", "Bearer " + authenticationResponse.getAccessToken())
                )
                .andExpect(status().is(200))
                .andDo(result -> {

                }).andReturn();
    }

    @AfterAll
    public void cleanup() {
        staffRepository.deleteById(this.authenticationResponse.getId());
    }
}