package com.penguin.esms.components.authentication.requests;

import lombok.*;

//@Data
//@Builder
@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    private String email;
    private String password;
}