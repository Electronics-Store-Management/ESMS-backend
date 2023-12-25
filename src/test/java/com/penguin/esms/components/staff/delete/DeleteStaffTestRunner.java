package com.penguin.esms.components.staff.delete;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.penguin.esms.EsmsApplication;
import com.penguin.esms.components.authentication.responses.AuthenticationResponse;
import com.penguin.esms.components.staff.Role;
import com.penguin.esms.components.staff.StaffEntity;
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
class DeleteStaffTestRunner {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TestService testService;
    private StaffEntity staff;

    private AuthenticationResponse authenticationResponse;
    @Autowired
    public DeleteStaffTestRunner(MockMvc mockMvc, ObjectMapper objectMapper, StaffRepository staffRepository, TestService testService) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.staffRepository = staffRepository;
        this.testService = testService;
    }


    @Test
    public void shouldDeleteStaff() throws Exception {
        newStaff();
        mockMvc.perform(MockMvcRequestBuilders.delete("/staff/" + staff.getId())
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                        .header("Authorization", "Bearer " + authenticationResponse.getAccessToken())
                )
                .andExpect(status().is(200))
                .andDo(print()).andReturn();
        CompletableFuture<Void> setupEntity = CompletableFuture.runAsync(() -> {
            try {
                staffRepository.deleteById(staff.getId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        setupEntity.join();
    }

    @Test
    public void shouldNotDeleteStaff() throws Exception {
        newStaff();
        mockMvc.perform(MockMvcRequestBuilders.delete("/staff/dfiu" + staff.getId())
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                        .header("Authorization", "Bearer " + authenticationResponse.getAccessToken())
                )
                .andExpect(status().is(404))
                .andDo(print()).andReturn();
        CompletableFuture<Void> setupEntity = CompletableFuture.runAsync(() -> {
            try {
                staffRepository.deleteById(staff.getId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        setupEntity.join();
    }

    @Test
    public void shouldNotDeleteDisSupplier() throws Exception {
        newDisStaff();
        mockMvc.perform(MockMvcRequestBuilders.delete("/staff/" + staff.getId())
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                        .header("Authorization", "Bearer " + authenticationResponse.getAccessToken())
                )
                .andExpect(status().is(400))
                .andDo(print()).andReturn();
        CompletableFuture<Void> setupEntity = CompletableFuture.runAsync(() -> {
            try {
                staffRepository.deleteById(staff.getId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        setupEntity.join();
    }

    public  void newStaff() throws Exception{
        StaffEntity entity = new StaffEntity();
        entity.setName(testService.generateRandomString(testService.ALL_CHARACTERS, 10));
        entity.setPhone("0" + testService.generateRandomString(testService.ALL_CHARACTERS, 9));
        entity.setEmail(testService.generateRandomString(testService.ALL_CHARACTERS, 9) + "@gmail.com");
        entity.setCitizenId(testService.generateRandomString(testService.NUMBER_CHARACTERS, 12));
        entity.setRole(Role.valueOf("STAFF"));
        CompletableFuture<Void> setupEntity = CompletableFuture.runAsync(() -> {
            try {
                this.staff = staffRepository.save(entity);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        setupEntity.join();
    }

    public  void newDisStaff() throws Exception{
        StaffEntity entity = new StaffEntity();
        entity.setName(testService.generateRandomString(testService.ALL_CHARACTERS, 10));
        entity.setPhone("0" + testService.generateRandomString(testService.ALL_CHARACTERS, 9));
        entity.setEmail(testService.generateRandomString(testService.ALL_CHARACTERS, 9) + "@gmail.com");
        entity.setCitizenId(testService.generateRandomString(testService.NUMBER_CHARACTERS, 12));
        entity.setRole(Role.valueOf("STAFF"));
        entity.setIsStopped(true);
        CompletableFuture<Void> setupEntity = CompletableFuture.runAsync(() -> {
            try {
                this.staff = staffRepository.save(entity);
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