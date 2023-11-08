package com.penguin.esms.components.category;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.penguin.esms.components.authentication.requests.AuthenticationRequest;
import com.penguin.esms.components.staff.Role;
import com.penguin.esms.components.staff.StaffEntity;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Random;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
class CheckDate {

    @Autowired
    private  MockMvc mockMvc;
    @Autowired
    private  ObjectMapper objectMapper;

    @Autowired
    public CheckDate(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    @DisplayName("UTCID01")
    @Order(1)
    public void shouldCreateUser() throws Exception {
        StaffEntity staffEntity = new StaffEntity();
        staffEntity.setName("Nguyen Hoang Hy");
        staffEntity.setPhone("00000");
        staffEntity.setPassword("123456");
        staffEntity.setEmail("hoanghy" + new Random().nextInt(1000000) + "@gmail.com");
        staffEntity.setCitizenId(String.valueOf(new Random().nextInt(1000000)));
        staffEntity.setRole(Role.ADMIN);

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(staffEntity)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.access_token").isString())
                .andExpect(jsonPath("$.refresh_token").exists())
                .andDo(print());
    }


    @Test
    @DisplayName("UTCID02")
    @Order(2)
    public void shouldNotCreateUser() throws Exception {
        StaffEntity staffEntity = new StaffEntity();
        staffEntity.setName("Nguyen Hoang Hy");
        staffEntity.setPhone("00000");
        staffEntity.setPassword("123456");
        staffEntity.setEmail("hoanghy2@gmail.com");
        staffEntity.setCitizenId("1234568");
        staffEntity.setRole(Role.ADMIN);

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(staffEntity)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    @DisplayName("UTCID03")
    @Order(3)
    public void shouldSignIn() throws Exception {
        AuthenticationRequest request = new AuthenticationRequest("hoanghy@gmail.com", "123456");

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.access_token").isString())
                .andExpect(jsonPath("$.refresh_token").exists())
                .andDo(print());
    }

    @Test
    @DisplayName("UTCID04")
    @Order(3)
    public void shouldNotSignIn() throws Exception {
        AuthenticationRequest request = new AuthenticationRequest("hoanghy@gmail.com", "123746");

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isForbidden())
                .andDo(print());
    }

    @Test
    @DisplayName("UTCID05")
    @Order(1)
    public void UTCID04() throws Exception {
        StaffEntity staffEntity = new StaffEntity();
        staffEntity.setName("Nguyen Hoang Hy");
        staffEntity.setPhone("00000");
        staffEntity.setPassword("123456");
        staffEntity.setEmail("hoanghy" + new Random().nextInt(1000000) + "@gmail.com");
        staffEntity.setCitizenId(String.valueOf(new Random().nextInt(1000000)));
        staffEntity.setRole(Role.ADMIN);

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(staffEntity)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.access_token").isString())
                .andExpect(jsonPath("$.refresh_token").exists())
                .andDo(print());
    }


    @Test
    @DisplayName("UTCID06")
    @Order(2)
    public void UTCID06() throws Exception {
        StaffEntity staffEntity = new StaffEntity();
        staffEntity.setName("Nguyen Hoang Hy");
        staffEntity.setPhone("00000");
        staffEntity.setPassword("123456");
        staffEntity.setEmail("hoanghy2@gmail.com");
        staffEntity.setCitizenId("1234568");
        staffEntity.setRole(Role.ADMIN);

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(staffEntity)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    @DisplayName("UTCID07")
    @Order(3)
    public void UTCID07() throws Exception {
        AuthenticationRequest request = new AuthenticationRequest("hoanghy@gmail.com", "123456");

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.access_token").isString())
                .andExpect(jsonPath("$.refresh_token").exists())
                .andDo(print());
    }

    @Test
    @DisplayName("UTCID08")
    @Order(3)
    public void UTCID08() throws Exception {
        AuthenticationRequest request = new AuthenticationRequest("hoanghy@gmail.com", "123746");

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isForbidden())
                .andDo(print());
    }


    @Test
    @DisplayName("UTCID09")
    @Order(2)
    public void UTCID09() throws Exception {
        StaffEntity staffEntity = new StaffEntity();
        staffEntity.setName("Nguyen Hoang Hy");
        staffEntity.setPhone("00000");
        staffEntity.setPassword("123456");
        staffEntity.setEmail("hoanghy2@gmail.com");
        staffEntity.setCitizenId("1234568");
        staffEntity.setRole(Role.ADMIN);

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(staffEntity)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }
}