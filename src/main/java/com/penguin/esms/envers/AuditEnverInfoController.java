package com.penguin.esms.envers;

import com.penguin.esms.components.staff.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RequestMapping("enver")
@RequiredArgsConstructor
@RestController
public class AuditEnverInfoController {
    private final AuditEnverInfoService auditEnverInfoService;
    @GetMapping("history")
    public ResponseEntity<?> getHistoryByStaff(@RequestParam String username) throws ClassNotFoundException {
        return ResponseEntity.ok(auditEnverInfoService.view(auditEnverInfoService.getRecord(username)));
    }
}
