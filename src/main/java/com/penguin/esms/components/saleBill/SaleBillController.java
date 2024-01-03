package com.penguin.esms.components.saleBill;

import com.penguin.esms.components.importBill.ImportBillEntity;
import com.penguin.esms.components.saleBill.dto.SaleBillDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "sale")
@RequiredArgsConstructor
@Getter
@Setter
public class SaleBillController {
    private final SaleBillService saleBillService;
//    @GetMapping("{id}")
//    public SaleBillEntity get(@PathVariable String id) {
//        return saleBillService.getSaleBill(id);
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> post(@RequestBody SaleBillDTO dto, Principal connectedUser) {
        return ResponseEntity.ok(saleBillService.post(dto, connectedUser));
    }

    @GetMapping("{id}")
    public List<?> get(@PathVariable String id) {
        return saleBillService.getRevisions(id);
    }
    @GetMapping()
    public List<?> get() {
        return saleBillService.getAll();
    }
    @GetMapping("history")
    public ResponseEntity<?> getAll(@RequestParam long start, @RequestParam long end) {
        if (start == end) return ResponseEntity.ok(saleBillService.getAll());
        return ResponseEntity.ok(saleBillService.getAllRevisions(new Date(start), new Date(end)));
    }
}
