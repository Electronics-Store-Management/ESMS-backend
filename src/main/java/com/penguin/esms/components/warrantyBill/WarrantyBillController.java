package com.penguin.esms.components.warrantyBill;

import com.penguin.esms.components.importBill.ImportBillEntity;
import com.penguin.esms.components.importBill.ImportBillService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "warranty")
@RequiredArgsConstructor
public class WarrantyBillController {
    private final WarrantyBillService warrantyBillService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> post(@RequestBody WarrantyBillEntity warrantyBillEntity, Principal connectedUser) {
        return ResponseEntity.ok(warrantyBillService.postWarrantyBill(warrantyBillEntity,connectedUser));
    }

    @GetMapping("{id}")
    public List<?> get(@PathVariable String id) {
        return warrantyBillService.getRevisions(id);
    }

    @GetMapping("history")
    public ResponseEntity<?> getAll(@RequestParam long start, @RequestParam long end) {
        return ResponseEntity.ok(warrantyBillService.getAllRevisions(new Date(start), new Date(end)));
    }
}
