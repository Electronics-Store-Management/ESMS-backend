package com.penguin.esms.components.product.viewById;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.penguin.esms.EsmsApplication;
import com.penguin.esms.components.authentication.responses.AuthenticationResponse;
import com.penguin.esms.components.customer.CustomerEntity;
import com.penguin.esms.components.customer.CustomerRepo;
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
class ViewProductByIdTestRunner {

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

    private AuthenticationResponse authenticationResponse;
    private ProductEntity product;

    @Autowired
    public ViewProductByIdTestRunner(MockMvc mockMvc, ObjectMapper objectMapper, ProductRepo productRepo, StaffRepository staffRepository, TestService testService) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.productRepo = productRepo;
        this.staffRepository = staffRepository;
        this.testService = testService;
    }

    @Test
    public void shouldGetProductById() throws Exception {
        newProductV();
        mockMvc.perform(MockMvcRequestBuilders.get("/product/" + product.getId())
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                        .header("Authorization", "Bearer " + authenticationResponse.getAccessToken())
                )
                .andExpect(status().is(200))
                .andDo(print()).andReturn();
        CompletableFuture<Void> setupEntity = CompletableFuture.runAsync(() -> {
            try {
                productRepo.deleteById(product.getId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        setupEntity.join();
    }

    @Test
    public void shouldNotGetProductById() throws Exception {
        newProductV();
        mockMvc.perform(MockMvcRequestBuilders.get("/product/dfiu" + product.getId())
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                        .header("Authorization", "Bearer " + authenticationResponse.getAccessToken())
                )
                .andExpect(status().is(404))
                .andDo(print()).andReturn();
        CompletableFuture<Void> setupEntity = CompletableFuture.runAsync(() -> {
            try {
                productRepo.deleteById(product.getId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        setupEntity.join();
    }

    @Test
    public void shouldNotGetDiscontinuedProductById() throws Exception {
        newDiscontinuedProductV();
        mockMvc.perform(MockMvcRequestBuilders.get("/product/" + product.getId())
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                        .header("Authorization", "Bearer " + authenticationResponse.getAccessToken())
                )
                .andExpect(status().is(400))
                .andDo(print()).andReturn();
        CompletableFuture<Void> setupEntity = CompletableFuture.runAsync(() -> {
            try {
                productRepo.deleteById(product.getId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        setupEntity.join();
    }
    public  void newProductV() throws Exception{
        ProductEntity neew = new ProductEntity();
        neew.setName(testService.generateRandomString(testService.ALL_CHARACTERS, 10));
        neew.setQuantity(20);
        neew.setPhotoURL(testService.generateRandomString(testService.ALL_CHARACTERS, 15));
        neew.setPrice(1000l);
        CompletableFuture<Void> setupEntity = CompletableFuture.runAsync(() -> {
            try {
                this.product = productRepo.save(neew);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        setupEntity.join();
    }

    public  void newDiscontinuedProductV() throws Exception{
        ProductEntity neew = new ProductEntity();
        neew.setName(testService.generateRandomString(testService.ALL_CHARACTERS, 10));
        neew.setQuantity(20);
        neew.setPhotoURL(testService.generateRandomString(testService.ALL_CHARACTERS, 15));
        neew.setPrice(1000l);
        neew.setIsStopped(true);
        CompletableFuture<Void> setupEntity = CompletableFuture.runAsync(() -> {
            try {
                this.product = productRepo.save(neew);
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