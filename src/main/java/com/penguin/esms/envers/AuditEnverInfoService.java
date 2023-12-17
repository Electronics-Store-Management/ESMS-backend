package com.penguin.esms.envers;

import com.penguin.esms.components.category.CategoryEntity;
import com.penguin.esms.components.staff.StaffEntity;
import com.penguin.esms.components.staff.StaffRepository;
import com.penguin.esms.entity.Error;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Setter
@Getter
public class AuditEnverInfoService {
    private final AuditEnversInfoRepo repo;
    private final StaffRepository staffRepo;

    public List<?> getRecord(String username) {
        if (username != null && !username.isEmpty()) {
            Optional<StaffEntity> staff = staffRepo.findByEmail(username);
            if (staff.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, new Error("staff not found").toString());
            }
        }
        List<AuditEnversInfo> audit = new ArrayList<AuditEnversInfo>();
        audit = repo.findByUsername(username);
        return audit;
    }

    public void view(List audit, String typeEntity){
        switch (typeEntity){
            case "ProductEntity":
                // return ve Entity.class cho tuwng truong hop
            case "StaffEntity":
            case "CategoryEntity":
            case "SupplierEntity":
            case "ImportBillEntity":
            case "SaleBillEntity":
            case "WarrantyBillEntity":
            default:
        }
    }
}
