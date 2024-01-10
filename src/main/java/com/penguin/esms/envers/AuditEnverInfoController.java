package com.penguin.esms.envers;

import com.penguin.esms.components.importBill.ImportBillService;
import com.penguin.esms.components.saleBill.SaleBillService;
import com.penguin.esms.components.staff.StaffEntity;
import com.penguin.esms.components.staff.StaffService;
import com.penguin.esms.components.warrantyBill.WarrantyBillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@RequestMapping("enver")
@RequiredArgsConstructor
@RestController
public class AuditEnverInfoController {
    private final ImportBillService importBillService;
    private final WarrantyBillService warrantyBillService;
    private final SaleBillService saleBillService;
    private final AuditEnverInfoService auditEnverInfoService;
    @GetMapping("history")
    public ResponseEntity<?> getHistoryByStaff(@RequestParam String username) throws ClassNotFoundException {
        return ResponseEntity.ok(auditEnverInfoService.view(auditEnverInfoService.getRecord(username)));
    }
//    @PostMapping
//    public void random(Principal connectedUser){
//        StaffEntity staff = (StaffEntity) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
//        importBillService.postImportBill(importBillService.random(), staff);
//        saleBillService.post(saleBillService.random(), staff);
//        warrantyBillService.postWarrantyBill(warrantyBillService.random(), staff);
//    }

    @PostMapping
    public void random() {
        auditEnverInfoService.randomData();
    }
}
