package com.penguin.esms.components.saleBill;

import com.penguin.esms.components.importBill.ImportBillEntity;
import com.penguin.esms.entity.Error;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SaleBillService {
    private final SaleBillRepo saleBillRepo;
    public SaleBillEntity getSaleBill(String saleBillId) {
        Optional<SaleBillEntity> saleBill = saleBillRepo.findById(saleBillId);
        if (saleBill.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sale bill not found");
        }
        return saleBill.get();
    }

    public SaleBillEntity postSaleBill(SaleBillEntity saleBillEntity) {
        return saleBillRepo.save(saleBillEntity);
    }
    public SaleBillEntity removeSaleBill(String id) {
        Optional<SaleBillEntity> saleBillEntityOptional = saleBillRepo.findById(id);
        if (saleBillEntityOptional.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, new Error("Sale bill not existed").toString());
        saleBillRepo.deleteById(id);
        return saleBillEntityOptional.get();
    }
}
