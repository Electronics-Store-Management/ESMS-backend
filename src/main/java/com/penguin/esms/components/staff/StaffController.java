package com.penguin.esms.components.staff;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("staff")
public class StaffController {
    @GetMapping("profile")
    public ResponseEntity<?> getStaffProfile(Principal connectedUser) {
//        System.out.println(connectedUser.getName());
//        StaffEntity staff = (StaffEntity) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
//        return ResponseEntity.ok(staff);
        return ResponseEntity.ok(connectedUser);
    }
}
