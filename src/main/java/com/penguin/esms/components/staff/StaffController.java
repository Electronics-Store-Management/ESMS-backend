package com.penguin.esms.components.staff;

import com.penguin.esms.components.staff.requests.NewStaffRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("staff")
@RequiredArgsConstructor
public class StaffController {

    private final StaffRepository staffRepository;

    @PostMapping("")
    @PreAuthorize("hasAuthority('CREATE:STAFF') or hasAuthority('ADMIN')")
    public ResponseEntity<?> createStaff(@RequestBody NewStaffRequest newStaff) {
        return  ResponseEntity.ok(staffRepository.save(new StaffEntity(newStaff.getName(), newStaff.getPhone(), newStaff.getPassword(), newStaff.getEmail(), newStaff.getCitizenId(), newStaff.getRole())));
    }

    @GetMapping("profile")
    public ResponseEntity<?> getStaffProfile(Principal connectedUser) {
        StaffEntity staff = (StaffEntity) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
//        return ResponseEntity.ok(staff);
        return ResponseEntity.ok(staff);
    }
}
