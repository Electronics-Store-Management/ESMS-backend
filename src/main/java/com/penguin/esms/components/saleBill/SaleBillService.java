package com.penguin.esms.components.saleBill;

import com.penguin.esms.components.importBill.ImportBillEntity;
import com.penguin.esms.components.staff.StaffEntity;
import com.penguin.esms.entity.Error;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
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

    public SaleBillEntity postSaleBill(SaleBillEntity saleBillEntity, Principal connectedUser) {
        StaffEntity staff = (StaffEntity) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        saleBillEntity.setStaffId(staff.getId());
        return saleBillRepo.save(saleBillEntity);
    }
}
