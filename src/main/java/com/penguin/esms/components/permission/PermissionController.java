package com.penguin.esms.components.permission;

import com.penguin.esms.components.permission.requests.PermissionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("permission")
@RequiredArgsConstructor
public class PermissionController {
    private final PermissionService permissionService;

    @PutMapping("{staffId}")
    @PreAuthorize("hasAuthority('UPDATE_ALL:PERMISSION') or hasAuthority('UPDATE_ITEM:PERMISSION:#staffId') or hasAuthority('ADMIN')")
    public ResponseEntity<?> updatePermission(@PathVariable String staffId, @RequestBody PermissionRequest permissionRequest) {
        return ResponseEntity.ok(permissionService.add(permissionRequest, staffId));
    }
}
