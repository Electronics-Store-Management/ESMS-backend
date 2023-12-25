package com.penguin.esms.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.penguin.esms.components.authentication.responses.AuthenticationResponse;
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

import java.security.SecureRandom;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Service
public class TestService {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    TestService(MockMvc mockMvc, ObjectMapper objectMapper, StaffRepository staffRepository) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.staffRepository = staffRepository;
    }

    public final String ALL_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    public final String NUMBER_CHARACTERS = "0123456789";
    private final SecureRandom secureRandom = new SecureRandom();
    public String generateRandomString(String characters, int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char randomChar = characters.charAt(secureRandom.nextInt(characters.length()));
            sb.append(randomChar);
        }
        return sb.toString();
    }

    public AuthenticationResponse getAuthenticationInfo() throws Exception {
        StaffEntity staffEntity = new StaffEntity();
        staffEntity.setName("Hoang Zanggg");
        staffEntity.setPhone("0" + generateRandomString(NUMBER_CHARACTERS, 9));
        staffEntity.setPassword("123456");
        staffEntity.setEmail("zang" + generateRandomString(NUMBER_CHARACTERS, 3) + "@gmail.com");
        staffEntity.setCitizenId(generateRandomString(NUMBER_CHARACTERS, 12));
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
                    String refreshToken = JsonPath.read(result.getResponse().getContentAsString(), "$.refresh_token");
                    authenticationResponse.setId(staffId);
                    authenticationResponse.setAccessToken(accessToken);
                    authenticationResponse.setRefreshToken(refreshToken);
                }).andReturn();

        return authenticationResponse;
    }

    public StaffEntity creatStaffEntity(String name, String phone, String email, String password, String citizendId, Role role) {
        StaffEntity staff = new StaffEntity();
        staff.setName(name);
        staff.setPhone(phone);
        staff.setPassword(password);
        staff.setEmail(email);
        staff.setCitizenId(citizendId);
        staff.setRole(role);

        AtomicReference<StaffEntity> result = null;

        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            try {
                result.set(staffRepository.save(staff));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        completableFuture.join();

        return result.get();
    }
}
