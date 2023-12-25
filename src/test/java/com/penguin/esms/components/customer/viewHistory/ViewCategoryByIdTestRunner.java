package com.penguin.esms.components.customer.viewHistory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.penguin.esms.EsmsApplication;
import com.penguin.esms.components.authentication.responses.AuthenticationResponse;
import com.penguin.esms.components.category.CategoryEntity;
import com.penguin.esms.components.category.CategoryRepo;
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
class ViewCategoryByIdTestRunner {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TestService testService;

    private AuthenticationResponse authenticationResponse;
    private CategoryEntity category;

    @Autowired
    public ViewCategoryByIdTestRunner(MockMvc mockMvc, ObjectMapper objectMapper, CategoryRepo categoryRepo, StaffRepository staffRepository, TestService testService) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.categoryRepo = categoryRepo;
        this.staffRepository = staffRepository;
        this.testService = testService;
    }

    @Test
    public void shouldViewCategoryHistoryById() throws Exception {
        CategoryEntity newCategory = new CategoryEntity();
        newCategory.setName(testService.generateRandomString(testService.ALL_CHARACTERS, 10));
        this.category = categoryRepo.save(newCategory);
        mockMvc.perform(MockMvcRequestBuilders.get("/category/history/" + category.getId())
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                        .header("Authorization", "Bearer " + authenticationResponse.getAccessToken())
                )
                .andExpect(status().is(200))
                .andDo(print()).andReturn();
    }

    @Test
    public void shouldNotViewCategoryHistoryById() throws Exception {
        CategoryEntity newCategory = new CategoryEntity();
        newCategory.setName(testService.generateRandomString(testService.ALL_CHARACTERS, 10));
        this.category = categoryRepo.save(newCategory);
        mockMvc.perform(MockMvcRequestBuilders.get("/category/history/dfiuyt347" + category.getId())
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                        .header("Authorization", "Bearer " + authenticationResponse.getAccessToken())
                )
                .andExpect(status().is(404))
                .andDo(print()).andReturn();
    }

    @Test
    public void shouldViewDiscontinuedCategoryHistoryById() throws Exception {
        CategoryEntity newCategory = new CategoryEntity();
        newCategory.setIsStopped(true);
        newCategory.setName(testService.generateRandomString(testService.ALL_CHARACTERS, 10));
        this.category = categoryRepo.save(newCategory);
        mockMvc.perform(MockMvcRequestBuilders.get("/category/history/" + category.getId())
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                        .header("Authorization", "Bearer " + authenticationResponse.getAccessToken())
                )
                .andExpect(status().is(200))
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