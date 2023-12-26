package com.penguin.esms.components.statistic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
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
class StatisticTestRunner {
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

    @Autowired
    public StatisticTestRunner(MockMvc mockMvc, ObjectMapper objectMapper, StaffRepository staffRepository, TestService testService) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.staffRepository = staffRepository;
        this.testService = testService;
    }

    @Test
    public void shouldRun() throws Exception {
        try {

            mockMvc.perform(MockMvcRequestBuilders.get("/statistic/name/name")
                            .header("Authorization", "Bearer " + authenticationResponse.getAccessToken())
                    )
                    .andDo(print()).andReturn();
        } catch (Exception e) {
        }
        try {

            mockMvc.perform(MockMvcRequestBuilders.get("/statistic/revenue?start=1703592691819&end=1703592691819")
                            .header("Authorization", "Bearer " + authenticationResponse.getAccessToken())
                    )
                    .andDo(print()).andReturn();
        } catch (Exception e) {
        }
        try {

            mockMvc.perform(MockMvcRequestBuilders.get("/statistic/revenue/1703592691819")
                            .header("Authorization", "Bearer " + authenticationResponse.getAccessToken())
                    )
                    .andDo(print()).andReturn();
        } catch (Exception e) {
        }
        try {

            mockMvc.perform(MockMvcRequestBuilders.get("/statistic/category?start=1703592691819&end=1703592691819")
                            .header("Authorization", "Bearer " + authenticationResponse.getAccessToken())
                    )
                    .andDo(print()).andReturn();
        } catch (Exception e) {
        }
        try {

            mockMvc.perform(MockMvcRequestBuilders.get("/statistic/cost?start=1703592691819&end=1703592691819")
                            .header("Authorization", "Bearer " + authenticationResponse.getAccessToken())
                    )
                    .andDo(print()).andReturn();
        } catch (Exception e) {
        }
        try {

            mockMvc.perform(MockMvcRequestBuilders.post("/product")
                            .header("Authorization", "Bearer " + authenticationResponse.getAccessToken())
                    )
                    .andDo(print()).andReturn();
        } catch (Exception e) {
        }
        try {

            mockMvc.perform(MockMvcRequestBuilders.get("/staff/profile")
                            .header("Authorization", "Bearer " + authenticationResponse.getAccessToken())
                    )
                    .andDo(print()).andReturn();
        } catch (Exception e) {
        }
        try {

            mockMvc.perform(MockMvcRequestBuilders.post("/staff")
                            .header("Authorization", "Bearer " + authenticationResponse.getAccessToken())
                    )
                    .andDo(
                            result -> {
                                mockMvc.perform(MockMvcRequestBuilders.get("/staff/history/" + JsonPath.read(result.getResponse().getContentAsString(), "$.id"))
                                                .header("Authorization", "Bearer " + authenticationResponse.getAccessToken())
                                        )
                                        .andDo(print()).andReturn();

                                mockMvc.perform(MockMvcRequestBuilders.get("/staff/resigned/" + JsonPath.read(result.getResponse().getContentAsString(), "$.name"))
                                                .header("Authorization", "Bearer " + authenticationResponse.getAccessToken())
                                        )
                                        .andDo(print()).andReturn();
                            }
                    ).andReturn();
        } catch (Exception e) {
        }
        try {

            mockMvc.perform(MockMvcRequestBuilders.get("/enver/history/username")
                            .header("Authorization", "Bearer " + authenticationResponse.getAccessToken())
                    )
                    .andDo(print()).andReturn();
        } catch (Exception e) {
        }
        try {

            mockMvc.perform(MockMvcRequestBuilders.post("/permission")
                            .header("Authorization", "Bearer " + authenticationResponse.getAccessToken())
                    )
                    .andDo(
                            result -> {
                                mockMvc.perform(MockMvcRequestBuilders.delete("/permission/" + JsonPath.read(result.getResponse().getContentAsString(), "$.id"))
                                                .header("Authorization", "Bearer " + authenticationResponse.getAccessToken())
                                        )
                                        .andDo(print()).andReturn();
                            }
                    ).andReturn();
        } catch (Exception e) {
        }
        try {

            mockMvc.perform(MockMvcRequestBuilders.get("/supplier/termination")
                            .header("Authorization", "Bearer " + authenticationResponse.getAccessToken())
                    )
                    .andDo(print()).andReturn();
        } catch (Exception e) {
        }
        try {

            mockMvc.perform(MockMvcRequestBuilders.post("/supplier")
                            .header("Authorization", "Bearer " + authenticationResponse.getAccessToken())
                    )
                    .andDo(
                            result -> {
                                mockMvc.perform(MockMvcRequestBuilders.get("/supplier/permission/" + JsonPath.read(result.getResponse().getContentAsString(), "$.permissionId"))
                                                .header("Authorization", "Bearer " + authenticationResponse.getAccessToken())
                                        )
                                        .andDo(print()).andReturn();
                            }
                    ).andReturn();
        } catch (Exception e) {
        }
        try {

            mockMvc.perform(MockMvcRequestBuilders.get("/customer/banned")
                            .header("Authorization", "Bearer " + authenticationResponse.getAccessToken())
                    )
                    .andDo(print()).andReturn();
        } catch (Exception e) {
        }
        try {

            mockMvc.perform(MockMvcRequestBuilders.get("/category/discontinued")
                            .header("Authorization", "Bearer " + authenticationResponse.getAccessToken())
                    )
                    .andDo(print()).andReturn();
        } catch (Exception e) {
        }
        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/warranty_product")
                            .header("Authorization", "Bearer " + authenticationResponse.getAccessToken())
                    )
                    .andDo(result -> {
                        mockMvc.perform(MockMvcRequestBuilders.get("/warranty_product/" + JsonPath.read(result.getResponse().getContentAsString(), "$.id"))
                                        .header("Authorization", "Bearer " + authenticationResponse.getAccessToken())
                                )
                                .andDo(print()).andReturn();
                    }).andReturn();

        } catch (Exception e) {
        }
        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/warranty_product")
                            .header("Authorization", "Bearer " + authenticationResponse.getAccessToken())
                    )
                    .andDo(result -> {
                        mockMvc.perform(MockMvcRequestBuilders.get("/sale_product/" + JsonPath.read(result.getResponse().getContentAsString(), "$.id"))
                                        .header("Authorization", "Bearer " + authenticationResponse.getAccessToken())
                                )
                        .andDo(print()).andReturn();
                    }).andReturn();
        } catch (Exception e) {
        }


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