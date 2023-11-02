package com.penguin.esms.components.authentication;

import com.penguin.esms.utils.Role;
import lombok.*;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String name;
    private String phone;
    private String password;
    private String email;
    private String citizenId;
    private Role role;
}