package com.penguin.esms.components.product.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.penguin.esms.EsmsApplication;
import com.penguin.esms.components.authentication.responses.AuthenticationResponse;
import com.penguin.esms.components.category.CategoryEntity;
import com.penguin.esms.components.category.CategoryRepo;
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
class ViewProductTestRunner {
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
    private ProductEntity product2;
    private CategoryEntity category2;

    private CategoryEntity category;



    @Autowired
    public ViewProductTestRunner(MockMvc mockMvc, ObjectMapper objectMapper, ProductRepo productRepo,CategoryRepo categoryRepo, StaffRepository staffRepository, TestService testService) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.productRepo = productRepo;
        this.staffRepository = staffRepository;
        this.testService = testService;
        this.categoryRepo = categoryRepo;
    }

    public static List<TestCase> testData() throws IOException {
        return TestUtils.readTestDataFromCsv("src\\test\\java\\com\\penguin\\esms\\components\\product\\view\\test-cases.csv", new ArrayList<>(List.of("name", "categoryName")), new ArrayList<>(List.of("status")));
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void shouldViewProduct(TestCase testCase) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/product?name=" + testCase.getInput().get("name")+"&category=" +testCase.getInput().get("categoryName"))
                        .header("Authorization", "Bearer " + authenticationResponse.getAccessToken())
                )
                .andExpect(status().is(Integer.parseInt(testCase.getExpected().get("status"))))
                .andDo(print()).andReturn();
    }

    @BeforeAll
    public void setup() throws Exception {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName("cariii");
        ProductEntity entity = new ProductEntity();

        CategoryEntity disCategoryEntity = new CategoryEntity();
        disCategoryEntity.setName("diss");
        disCategoryEntity.setIsStopped(true);
        ProductEntity entity2 = new ProductEntity();
        entity2.setName("ttttt");
        entity2.setCategory(category2);

        CompletableFuture<Void> setupEntity = CompletableFuture.runAsync(() -> {
            this.product2 = productRepo.save(entity2);
            this.category= categoryRepo.save(categoryEntity);
            this.category2=categoryRepo.save(disCategoryEntity);
            try {
                authenticationResponse = testService.getAuthenticationInfo();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        setupEntity.join();
        entity.setName("produttt");
        entity.setCategory(category);

        CompletableFuture<Void> setupEntity2 = CompletableFuture.runAsync(() -> {
            this.product = productRepo.save(entity);
            try {
                authenticationResponse = testService.getAuthenticationInfo();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        setupEntity2.join();
    }

    @AfterAll
    public void cleanup() {
        productRepo.deleteById(this.product.getId());
        categoryRepo.deleteById(this.category.getId());
        productRepo.deleteById(this.product2.getId());
        categoryRepo.deleteById(this.category2.getId());
        staffRepository.deleteById(this.authenticationResponse.getId());
    }
}