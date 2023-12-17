package com.penguin.esms.envers;

import com.penguin.esms.components.category.CategoryEntity;
import com.penguin.esms.components.importBill.ImportBillEntity;
import com.penguin.esms.components.product.ProductEntity;
import com.penguin.esms.components.staff.Role;
import com.penguin.esms.components.staff.StaffDTO;
import com.penguin.esms.components.staff.StaffEntity;
import com.penguin.esms.components.staff.StaffRepository;
import com.penguin.esms.entity.Error;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Setter
@Getter
public class AuditEnverInfoService {
    private final AuditEnversInfoRepo repo;
    private final StaffRepository staffRepo;
    private final EntityManager entityManager;

    public List<AuditEnversInfo> getRecord(String username) {
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

    public List<?> view(List<AuditEnversInfo> audit) throws ClassNotFoundException {
        List<AuditEnversInfo> auditByStaff = new ArrayList<AuditEnversInfo>();
        AuditReader auditReader = AuditReaderFactory.get(entityManager);
        List<String> typeEntity = new ArrayList<>();
        typeEntity.add("com.penguin.esms.components.product.ProductEntity");
        typeEntity.add("com.penguin.esms.components.permission.PermissionEntity");
        typeEntity.add("com.penguin.esms.components.staff.StaffEntity");
        typeEntity.add("com.penguin.esms.components.category.CategoryEntity");
        typeEntity.add("com.penguin.esms.components.supplier.SupplierEntity");
        typeEntity.add("com.penguin.esms.components.importBill.ImportBillEntity");
        typeEntity.add("com.penguin.esms.components.saleBill.SaleBillEntity");
        typeEntity.add("com.penguin.esms.components.warrantyBill.WarrantyBillEntity");
        for (String i : typeEntity){
            Class<?> type = Class.forName(i);
            AuditQuery query = auditReader.createQuery()
                    .forRevisionsOfEntity(type, true, true)
                    .add(AuditEntity.revisionNumber().in((Collection) audit.stream().map(auditEnversInfo -> auditEnversInfo.getId()).collect(Collectors.toList())))
                    .addProjection(AuditEntity.revisionNumber())
                    .addProjection(AuditEntity.revisionType())
                    .addOrder(AuditEntity.revisionNumber().desc());
            auditByStaff.addAll(query.getResultList());
        }
        return auditByStaff;
    }
}
