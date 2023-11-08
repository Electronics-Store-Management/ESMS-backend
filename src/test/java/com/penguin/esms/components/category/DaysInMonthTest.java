package com.penguin.esms.components.category;

import com.fasterxml.jackson.databind.ObjectMapper;
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
class DaysInMonthTest {

    @Autowired
    private  MockMvc mockMvc;
    @Autowired
    private  ObjectMapper objectMapper;

    @Autowired
    public DaysInMonthTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    @DisplayName("UTC_DaysInMonth_01")
    @Order(1)
    public void UTC_DaysInMonth_01() throws Exception {
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
    @DisplayName("UTC_DaysInMonth_02")
    @Order(1)
    public void UTC_DaysInMonth_02() throws Exception {
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
    @DisplayName("UTC_DaysInMonth_03")
    @Order(1)
    public void UTC_DaysInMonth_03() throws Exception {
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
    @DisplayName("UTC_DaysInMonth_04")
    @Order(1)
    public void UTC_DaysInMonth_04() throws Exception {
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
    @DisplayName("UTC_DaysInMonth_05")
    @Order(1)
    public void UTC_DaysInMonth_05() throws Exception {
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
    @DisplayName("UTC_DaysInMonth_06")
    @Order(1)
    public void UTC_DaysInMonth_06() throws Exception {
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
    @DisplayName("UTC_DaysInMonth_07")
    @Order(1)
    public void UTC_DaysInMonth_07() throws Exception {
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
    @DisplayName("UTC_DaysInMonth_08")
    @Order(1)
    public void UTC_DaysInMonth_08() throws Exception {
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
    @DisplayName("UTC_DaysInMonth_09")
    @Order(1)
    public void UTC_DaysInMonth_09() throws Exception {
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
    @DisplayName("UTC_DaysInMonth_10")
    @Order(1)
    public void UTC_DaysInMonth_10() throws Exception {
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
    @DisplayName("UTC_DaysInMonth_11")
    @Order(1)
    public void UTC_DaysInMonth_11() throws Exception {
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
    @DisplayName("UTC_DaysInMonth_12")
    @Order(1)
    public void UTC_DaysInMonth_12() throws Exception {
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
    @DisplayName("UTC_DaysInMonth_13")
    @Order(1)
    public void UTC_DaysInMonth_13() throws Exception {
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
    @DisplayName("UTC_DaysInMonth_14")
    @Order(1)
    public void UTC_DaysInMonth_14() throws Exception {
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
    @DisplayName("UTC_DaysInMonth_15")
    @Order(1)
    public void UTC_DaysInMonth_15() throws Exception {
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
}