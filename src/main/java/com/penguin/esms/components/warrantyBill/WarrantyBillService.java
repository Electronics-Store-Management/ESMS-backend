package com.penguin.esms.components.warrantyBill;

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
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WarrantyBillService {
    private final WarrantyBillRepo warrantyBillRepo;
    public Boolean checkWarranty(Date buyDate, Period periodTime){
        if (Period.between(LocalDate.now(), buyDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()).getMonths() > periodTime.getMonths())
            return false;
        return true;
    }
    public WarrantyBillEntity getWarrantyBill(String warrantyBillId) {
        Optional<WarrantyBillEntity> warrantyBill = warrantyBillRepo.findById(warrantyBillId);
        if (warrantyBill.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, new Error("Warranty bill not found").toString());
        }
        return warrantyBill.get();
    }

    public WarrantyBillEntity postWarrantyBill(WarrantyBillEntity warrantyBillEntity, Principal connectedUser) {
        StaffEntity staff = (StaffEntity) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        warrantyBillEntity.setStaffId(staff.getId());
        return warrantyBillRepo.save(warrantyBillEntity);
    }
}
