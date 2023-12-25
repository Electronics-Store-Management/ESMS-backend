package com.penguin.esms.components.supplier.delete;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.penguin.esms.EsmsApplication;
import com.penguin.esms.components.authentication.responses.AuthenticationResponse;
import com.penguin.esms.components.customer.CustomerEntity;
import com.penguin.esms.components.customer.CustomerRepo;
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
class DeleteCustomerTestRunner {

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
    public DeleteCustomerTestRunner(MockMvc mockMvc, ObjectMapper objectMapper, CustomerRepo customerRepo, StaffRepository staffRepository, TestService testService) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.customerRepo = customerRepo;
        this.staffRepository = staffRepository;
        this.testService = testService;
    }

    @Test
    public void shouldDeleteCustomer() throws Exception {
        newCustomer();
        mockMvc.perform(MockMvcRequestBuilders.delete("/customer/" + customer.getId())
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                        .header("Authorization", "Bearer " + authenticationResponse.getAccessToken())
                )
                .andExpect(status().is(200))
                .andDo(print()).andReturn();
        CompletableFuture<Void> setupEntity = CompletableFuture.runAsync(() -> {
            try {
                customerRepo.deleteById(customer.getId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        setupEntity.join();
    }

    @Test
    public void shouldNotDeleteCustomer() throws Exception {
        newCustomer();
        mockMvc.perform(MockMvcRequestBuilders.delete("/customer/dfiu" + customer.getId())
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                        .header("Authorization", "Bearer " + authenticationResponse.getAccessToken())
                )
                .andExpect(status().is(404))
                .andDo(print()).andReturn();
        CompletableFuture<Void> setupEntity = CompletableFuture.runAsync(() -> {
            try {
                customerRepo.deleteById(customer.getId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        setupEntity.join();
    }

    @Test
    public void shouldNotDeleteBannedCustomer() throws Exception {
        newBannedCustomer();
        mockMvc.perform(MockMvcRequestBuilders.delete("/customer/" + customer.getId())
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                        .header("Authorization", "Bearer " + authenticationResponse.getAccessToken())
                )
                .andExpect(status().is(400))
                .andDo(print()).andReturn();
        CompletableFuture<Void> setupEntity = CompletableFuture.runAsync(() -> {
            try {
                customerRepo.deleteById(customer.getId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        setupEntity.join();
    }

    public  void newCustomer() throws Exception{
        CustomerEntity newCustomer = new CustomerEntity();
        newCustomer.setName(testService.generateRandomString(testService.ALL_CHARACTERS, 10));
        newCustomer.setPhone("0" + testService.generateRandomString(testService.ALL_CHARACTERS, 9));
        newCustomer.setAddress(testService.generateRandomString(testService.ALL_CHARACTERS, 10));
        CompletableFuture<Void> setupEntity = CompletableFuture.runAsync(() -> {
            try {
                this.customer = customerRepo.save(newCustomer);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        setupEntity.join();
    }

    public  void newBannedCustomer() throws Exception{
        CustomerEntity newCustomer = new CustomerEntity();
        newCustomer.setName(testService.generateRandomString(testService.ALL_CHARACTERS, 10));
        newCustomer.setPhone("0" + testService.generateRandomString(testService.ALL_CHARACTERS, 9));
        newCustomer.setAddress(testService.generateRandomString(testService.ALL_CHARACTERS, 10));
        newCustomer.setIsStopped(true);
        CompletableFuture<Void> setupEntity = CompletableFuture.runAsync(() -> {
            try {
                this.customer = customerRepo.save(newCustomer);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        setupEntity.join();
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