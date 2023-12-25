package com.penguin.esms.components.product.viewHistory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.penguin.esms.EsmsApplication;
import com.penguin.esms.components.authentication.responses.AuthenticationResponse;
import com.penguin.esms.components.category.CategoryEntity;
import com.penguin.esms.components.category.CategoryRepo;
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
class ViewHistoryProductByIdTestRunner {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TestService testService;

    private AuthenticationResponse authenticationResponse;
    private ProductEntity product;
    private CategoryEntity category;


    @Autowired
    public ViewHistoryProductByIdTestRunner(MockMvc mockMvc, ObjectMapper objectMapper, ProductRepo productRepo,CategoryRepo categoryRepo, StaffRepository staffRepository, TestService testService) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.productRepo = productRepo;
        this.staffRepository = staffRepository;
        this.testService = testService;
        this.categoryRepo = categoryRepo;
    }

    @Test
    public void shouldGetHistoryProductById() throws Exception {
        CompletableFuture<Void> setupEntity0 = CompletableFuture.runAsync(() -> {
            try {
                newHistoryProductV();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        setupEntity0.join();
//        newHistoryProductV();
        mockMvc.perform(MockMvcRequestBuilders.get("/product/history/" + product.getId())
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                        .header("Authorization", "Bearer " + authenticationResponse.getAccessToken())
                )
                .andExpect(status().is(200))
                .andDo(print()).andReturn();
        CompletableFuture<Void> setupEntity = CompletableFuture.runAsync(() -> {
            try {
                productRepo.deleteById(product.getId());
                categoryRepo.deleteById(category.getId());

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        setupEntity.join();
    }

    @Test
    public void shouldNotGetHistoryProductById() throws Exception {
        CompletableFuture<Void> setupEntity0 = CompletableFuture.runAsync(() -> {
            try {
                newHistoryProductV();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        setupEntity0.join();
//        newHistoryProductV();
        mockMvc.perform(MockMvcRequestBuilders.get("/product/history/dsdfd3fiu" + product.getId())
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                        .header("Authorization", "Bearer " + authenticationResponse.getAccessToken())
                )
                .andExpect(status().is(404))
                .andDo(print()).andReturn();
        CompletableFuture<Void> setupEntity = CompletableFuture.runAsync(() -> {
            try {
                productRepo.deleteById(product.getId());
                categoryRepo.deleteById(category.getId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        setupEntity.join();
    }

    @Test
    public void shouldGetDiscontinuedHistoryProductById() throws Exception {
        CompletableFuture<Void> setupEntity0 = CompletableFuture.runAsync(() -> {
            try {
                newDiscontinuedHistoryProductV();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        setupEntity0.join();
//        newDiscontinuedHistoryProductV();
        mockMvc.perform(MockMvcRequestBuilders.get("/product/history/" + product.getId())
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                        .header("Authorization", "Bearer " + authenticationResponse.getAccessToken())
                )
                .andExpect(status().is(200))
                .andDo(print()).andReturn();
        CompletableFuture<Void> setupEntity = CompletableFuture.runAsync(() -> {
            try {
                productRepo.deleteById(product.getId());

                categoryRepo.deleteById(category.getId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        setupEntity.join();
    }
    public  void newHistoryProductV() throws Exception{
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName("giang");

        ProductEntity neeew = new ProductEntity();

        CompletableFuture<Void> setupEntity = CompletableFuture.runAsync(() -> {
            try {
                this.category = categoryRepo.save(categoryEntity);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        setupEntity.join();
        neeew.setName(testService.generateRandomString(testService.ALL_CHARACTERS, 10));
        neeew.setCategory(categoryEntity);
        neeew.setUnit("cai");
        neeew.setPrice(1000l);
        neeew.setQuantity(20);
        neeew.setWarrantyPeriod(6);
        neeew.setPhotoURL(testService.generateRandomString(testService.ALL_CHARACTERS, 15));

        CompletableFuture<Void> setupEntity2 = CompletableFuture.runAsync(() -> {
            try {
                this.product = productRepo.save(neeew);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        setupEntity2.join();
    }

    public  void newDiscontinuedHistoryProductV() throws Exception{
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName("giang");

        ProductEntity neeew = new ProductEntity();
        CompletableFuture<Void> setupEntity = CompletableFuture.runAsync(() -> {
            try {
                this.category = categoryRepo.save(categoryEntity);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        setupEntity.join();

        neeew.setName(testService.generateRandomString(testService.ALL_CHARACTERS, 10));
        neeew.setCategory(categoryEntity);
        neeew.setUnit("cai");
        neeew.setPrice(1000l);
        neeew.setQuantity(20);
        neeew.setWarrantyPeriod(6);
        neeew.setPhotoURL(testService.generateRandomString(testService.ALL_CHARACTERS, 15));
        CompletableFuture<Void> setupEntity2 = CompletableFuture.runAsync(() -> {
            try {
                this.product = productRepo.save(neeew);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        setupEntity2.join();
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