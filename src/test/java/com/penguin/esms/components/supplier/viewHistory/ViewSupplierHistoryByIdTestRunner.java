package com.penguin.esms.components.supplier.viewHistory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.penguin.esms.EsmsApplication;
import com.penguin.esms.components.authentication.responses.AuthenticationResponse;
import com.penguin.esms.components.customer.CustomerEntity;
import com.penguin.esms.components.customer.CustomerRepo;
import com.penguin.esms.components.staff.StaffRepository;
import com.penguin.esms.components.supplier.SupplierEntity;
import com.penguin.esms.components.supplier.SupplierRepo;
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
class ViewSupplierHistoryByIdTestRunner {

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
    public ViewSupplierHistoryByIdTestRunner(MockMvc mockMvc, ObjectMapper objectMapper, SupplierRepo supplierRepo, StaffRepository staffRepository, TestService testService) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.supplierRepo = supplierRepo;
        this.staffRepository = staffRepository;
        this.testService = testService;
    }

    @Test
    public void shouldGetHisSupplierById() throws Exception {
        newSupplierH();
        mockMvc.perform(MockMvcRequestBuilders.get("/supplier/history/" + supplier.getId())
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                        .header("Authorization", "Bearer " + authenticationResponse.getAccessToken())
                )
                .andExpect(status().is(200))
                .andDo(print()).andReturn();
        CompletableFuture<Void> setupEntity = CompletableFuture.runAsync(() -> {
            try {
                supplierRepo.deleteById(supplier.getId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        setupEntity.join();
    }

    @Test
    public void shouldNotGetHisSupplierById() throws Exception {
        newSupplierH();
        mockMvc.perform(MockMvcRequestBuilders.get("/supplier/history/dfiu" + supplier.getId())
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                        .header("Authorization", "Bearer " + authenticationResponse.getAccessToken())
                )
                .andExpect(status().is(404))
                .andDo(print()).andReturn();
        CompletableFuture<Void> setupEntity = CompletableFuture.runAsync(() -> {
            try {
                supplierRepo.deleteById(supplier.getId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        setupEntity.join();
    }

    @Test
    public void shouldGetDisHisSupplierById() throws Exception {
        newDisSupplierH();
        mockMvc.perform(MockMvcRequestBuilders.get("/supplier/history/" + supplier.getId())
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                        .header("Authorization", "Bearer " + authenticationResponse.getAccessToken())
                )
                .andExpect(status().is(200))
                .andDo(print()).andReturn();
        CompletableFuture<Void> setupEntity = CompletableFuture.runAsync(() -> {
            try {
                supplierRepo.deleteById(supplier.getId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        setupEntity.join();
    }
    public  void newSupplierH() throws Exception{
        SupplierEntity neew = new SupplierEntity();
        neew.setName(testService.generateRandomString(testService.ALL_CHARACTERS, 10));
        neew.setEmail(testService.generateRandomString(testService.ALL_CHARACTERS, 9) + "@gmail.com");
        neew.setPhone("0" + testService.generateRandomString(testService.ALL_CHARACTERS, 9));
        neew.setAddress(testService.generateRandomString(testService.ALL_CHARACTERS, 10));
//        neew.setNote(testService.generateRandomString(testService.ALL_CHARACTERS, 20));
        CompletableFuture<Void> setupEntity = CompletableFuture.runAsync(() -> {
            try {
                this.supplier = supplierRepo.save(neew);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        setupEntity.join();
    }

    public  void newDisSupplierH() throws Exception{
        SupplierEntity neew = new SupplierEntity();
        neew.setName(testService.generateRandomString(testService.ALL_CHARACTERS, 10));
        neew.setEmail(testService.generateRandomString(testService.ALL_CHARACTERS, 9) + "@gmail.com");
        neew.setPhone("0" + testService.generateRandomString(testService.ALL_CHARACTERS, 9));
        neew.setAddress(testService.generateRandomString(testService.ALL_CHARACTERS, 10));
//        neew.setNote(testService.generateRandomString(testService.ALL_CHARACTERS, 20));
        neew.setIsStopped(true);
        CompletableFuture<Void> setupEntity = CompletableFuture.runAsync(() -> {
            try {
                this.supplier = supplierRepo.save(neew);
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