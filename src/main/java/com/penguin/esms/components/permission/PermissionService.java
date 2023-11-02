package com.penguin.esms.components.permission;

import com.penguin.esms.components.permission.requests.PermissionRequest;
import com.penguin.esms.components.staff.StaffEntity;
import com.penguin.esms.components.staff.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PermissionService {
    private final PermissionRepo permissionRepo;
    private final StaffRepository staffRepository;

    public PermissionEntity add(PermissionRequest permissionRequest, String staffId) {
        Optional<StaffEntity> staff = staffRepository.findById(staffId);

        PermissionEntity permission = new PermissionEntity();
        permission.setPermissionType(permissionRequest.getPermissionType());
        permission.setEntityType(permissionRequest.getEntityType());
        permission.setEntityId(permissionRequest.getEntityId());
        staff.ifPresent(permission::setStaff);

        return permissionRepo.save(permission);
    }

    public void remove(String permissionId) {
//        Optional<PermissionEntity> permission = permissionRepo.findByPermissionTypeAndEntityTypeAndEntityIdAndStaffId(permissionRequest.getPermissionType().name(), permissionRequest.getEntityType().name(), permissionRequest.getEntityId(), staffId);
//        permission.ifPresent(permissionRepo::delete);
        permissionRepo.findById(permissionId);
    }
}
