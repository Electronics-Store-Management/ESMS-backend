package com.penguin.esms.components.permission;

import com.penguin.esms.components.permission.dto.PermissionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("permission")
@RequiredArgsConstructor
public class PermissionController {
    private final PermissionService permissionService;

    @PostMapping("")
    @PreAuthorize("hasAuthority('UPDATE_ALL:PERMISSION') or hasAuthority('UPDATE_ITEM:PERMISSION:#staffId') or hasAuthority('ADMIN')")
    public ResponseEntity<?> addPermission(@RequestBody PermissionRequest permissionRequest) {
        return ResponseEntity.ok(permissionService.add(permissionRequest, permissionRequest.getStaffId()));
    }

    @DeleteMapping("{permissionId}")
    @PreAuthorize("hasAuthority('DELETE_ALL:PERMISSION') or hasAuthority('DELETE_ITEM:PERMISSION:#staffId') or hasAuthority('ADMIN')")
    public ResponseEntity<?> deletePermission(@PathVariable String permissionId) {
        permissionService.remove(permissionId);
        return ResponseEntity.ok().build();
    }
}
