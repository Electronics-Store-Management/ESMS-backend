package com.penguin.esms.components.staff;

import com.penguin.esms.components.staff.requests.NewStaffRequest;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.sql.SQLException;

@RestController
@RequestMapping("staff")
@RequiredArgsConstructor
@ControllerAdvice
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
        System.out.println(staff.getPermissions());
        return ResponseEntity.ok(staff);
    }

    @ExceptionHandler({ ResponseStatusException.class })
    public ResponseEntity<?> handleException(ResponseStatusException e) {
        return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
    }

    @ExceptionHandler({ HttpMessageNotReadableException.class })
    public ResponseEntity<?> handleNotReadableException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Request body not found");
    }

    @ExceptionHandler({ SQLException.class })
    public ResponseEntity<?> handleSQLException(SQLException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Request body not found");
    }
}
