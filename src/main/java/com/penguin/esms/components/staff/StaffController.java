package com.penguin.esms.components.staff;

import com.penguin.esms.components.category.CategoryDTO;
import com.penguin.esms.components.category.CategoryEntity;
import com.penguin.esms.components.staff.requests.NewStaffRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
    private final StaffService staffService;

    @PostMapping("")
    @PreAuthorize("hasAuthority('CREATE:STAFF') or hasAuthority('ADMIN')")
    public ResponseEntity<?> createStaff(@Valid @RequestBody NewStaffRequest newStaff) {
        return  ResponseEntity.ok(staffRepository.save(new StaffEntity(newStaff.getName(), newStaff.getPhone(), newStaff.getPassword(), newStaff.getEmail(), newStaff.getCitizenId(), newStaff.getRole())));
    }

    @GetMapping("profile")
    public ResponseEntity<?> getStaffProfile(Principal connectedUser) {
        StaffEntity staff = (StaffEntity) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        return ResponseEntity.ok(staff);
    }

    @GetMapping("")
    public ResponseEntity<?> getList(@RequestParam(defaultValue = "") String name) {
        return ResponseEntity.ok(staffService.findByName(name));
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('VIEW_ITEM:STAFF:' + #id) or hasAuthority('VIEW_ALL:STAFF') or hasAuthority('ADMIN')")
    public ResponseEntity<?> getStaffById(@PathVariable String id) {
        if (staffRepository.findById(id).isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(staffRepository.findById(id));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<?>  edit(@RequestBody StaffDTO staffDTO, @PathVariable String id) {
        return ResponseEntity.ok(staffService.update(staffDTO, id));
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable String id) {
        staffService.remove(id);
    }
}
