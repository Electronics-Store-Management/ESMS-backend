package com.penguin.esms.components.staff;

import com.penguin.esms.components.permission.PermissionService;
import com.penguin.esms.components.permission.dto.StaffPermissionResponse;
import com.penguin.esms.services.AmazonS3Service;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("staff")
@RequiredArgsConstructor
@ControllerAdvice
public class StaffController {

    private final StaffRepository staffRepository;
    private final StaffService staffService;
    private final PermissionService permissionService;
    private final AmazonS3Service amazonS3Service;

//    @PostMapping("")
//    @PreAuthorize("hasAuthority('CREATE:STAFF') or hasAuthority('ADMIN')")
//    public ResponseEntity<?> createStaff(@Valid @RequestBody NewStaffRequest newStaff) {
//        return  ResponseEntity.ok(staffRepository.save(new StaffEntity(newStaff.getName(), newStaff.getPhone(), newStaff.getPassword(), newStaff.getEmail(), newStaff.getCitizenId(), newStaff.getRole())));
//    }
    @PostMapping("")
    public ResponseEntity<?> postStaff(@RequestParam @Nullable MultipartFile photo, StaffDTO dto) throws Exception {
        if (photo != null){
            String objectURL = amazonS3Service.addFile(photo, dto.getName() + "_" + photo.getOriginalFilename());
            dto.setPhotoURL(objectURL);
        }

        return ResponseEntity.ok(staffService.addStaff(dto));
    }
    @GetMapping("profile")
    public ResponseEntity<?> getStaffProfile(Principal connectedUser) {
        StaffEntity staff = (StaffEntity) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        return ResponseEntity.ok(staff);
    }
    @GetMapping("resigned")
    @PreAuthorize("hasAuthority('VIEW_ITEM:STAFF:' + #id) or hasAuthority('VIEW_ALL:STAFF') or hasAuthority('ADMIN')")
    public List<StaffEntity> getAllResigned(@RequestParam(defaultValue = "") String name) {
        return staffService.findResigned(name);
    }
    @GetMapping("history/{id}")
    public List<?> getALlHistory(@PathVariable String id) {
        return staffService.getRevisionsForStaff(id);
    }
    @GetMapping
    @PreAuthorize("hasAuthority('VIEW_ITEM:STAFF:' + #id) or hasAuthority('VIEW_ALL:STAFF') or hasAuthority('ADMIN')")
    public List<StaffEntity> getAll(@RequestParam(defaultValue = "") String name) {
        return staffService.findByName(name);
    }

    @GetMapping("username")
    @PreAuthorize("hasAuthority('VIEW_ITEM:STAFF:' + #id) or hasAuthority('VIEW_ALL:STAFF') or hasAuthority('ADMIN')")
    public StaffEntity getStaffByEmail(@RequestParam(defaultValue = "") String email) {
        return staffService.findByMail(email);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('VIEW_ITEM:STAFF:' + #id) or hasAuthority('VIEW_ALL:STAFF') or hasAuthority('ADMIN')")
    public StaffEntity getStaffById(@PathVariable String id) {
        return staffService.getOne(id);
    }

    @GetMapping("{id}/permission")
    public StaffPermissionResponse getPermsision(@PathVariable String id) {
        return permissionService.getStaffPermission(id);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<?>  edit(@RequestBody StaffDTO dto, @PathVariable String id) throws IOException {
        return ResponseEntity.ok(staffService.update(dto, id));
    }
    @PutMapping(path = "changePassword")
    public void changePass(@RequestParam String oldPassword, @RequestParam String newPassword, Principal connectedUser) throws Exception {
        staffService.changePassword(oldPassword, newPassword, connectedUser);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable String id) {
        staffService.remove(id);
    }
}
