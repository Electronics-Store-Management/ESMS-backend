package com.penguin.esms.components.staff.requests;

import com.penguin.esms.components.staff.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class NewStaffRequest {
    private String name;
    private String phone;
    private String password;
    private String email;
    private String citizenId;
    private Role role;
}
