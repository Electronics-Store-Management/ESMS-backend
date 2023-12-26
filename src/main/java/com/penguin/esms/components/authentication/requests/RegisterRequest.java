package com.penguin.esms.components.authentication.requests;

import com.penguin.esms.components.staff.Role;
import lombok.*;

@Getter
@Setter
//@Data
//@Builder
//@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String name;
    private String phone;
    private String password;
    private String email;
    private String citizenId;
    private Role role;
}