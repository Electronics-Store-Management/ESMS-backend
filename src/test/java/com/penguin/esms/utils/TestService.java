package com.penguin.esms.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.penguin.esms.components.authentication.responses.AuthenticationResponse;
import com.penguin.esms.components.category.CategoryRepo;
import com.penguin.esms.components.staff.Role;
import com.penguin.esms.components.staff.StaffEntity;
import com.penguin.esms.components.staff.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Random;
import java.util.random.RandomGenerator;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Service
public class TestService {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    TestService(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    public AuthenticationResponse getAuthenticationInfo() throws Exception {
        StaffEntity staffEntity = new StaffEntity();
        staffEntity.setName("Nguyen Hoang Hy");
        staffEntity.setPhone("0000011112");
        staffEntity.setPassword("123456");
        staffEntity.setEmail("hoanghyyy@gmail.com");
        staffEntity.setCitizenId("052203000411");
        staffEntity.setRole(Role.ADMIN);

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();

        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(staffEntity)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.access_token").isString())
                .andExpect(jsonPath("$.refresh_token").exists())
                .andDo(MockMvcResultHandlers.print())
                .andDo(result -> {
                    String staffId = JsonPath.read(result.getResponse().getContentAsString(), "$.id");
                    String accessToken = JsonPath.read(result.getResponse().getContentAsString(), "$.access_token");
                    authenticationResponse.setId(staffId);
                    authenticationResponse.setAccessToken(accessToken);
                }).andReturn();

        return authenticationResponse;
    }
}
