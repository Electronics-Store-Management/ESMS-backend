package com.penguin.esms.components.warrantyBill;

import com.penguin.esms.components.importBill.ImportBillEntity;
import com.penguin.esms.components.importBill.dto.ImportBillDTO;
import com.penguin.esms.components.staff.Role;
import com.penguin.esms.components.staff.StaffDTO;
import com.penguin.esms.components.staff.StaffEntity;
import com.penguin.esms.components.warrantyBill.dto.WarrantyBillDTO;
import com.penguin.esms.entity.Error;
import com.penguin.esms.envers.AuditEnversInfo;
import com.penguin.esms.envers.AuditEnversInfoRepo;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WarrantyBillService {
    private final EntityManager entityManager;
    private final AuditEnversInfoRepo auditEnversInfoRepo;
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
    public List<?> getRevisions(String id) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);

        AuditQuery query = auditReader.createQuery()
                .forRevisionsOfEntity(WarrantyBillEntity.class, true, true)
                .add(AuditEntity.id().eq(id))
                .addProjection(AuditEntity.revisionNumber())
                .addProjection(AuditEntity.property("staffId"))
                .addProjection(AuditEntity.property("customer_id"))
                .addProjection(AuditEntity.property("warrantyDate"))
                .addProjection(AuditEntity.revisionType())
                .addOrder(AuditEntity.revisionNumber().desc());

        List<AuditEnversInfo> audit = new ArrayList<AuditEnversInfo>();
        List<Object[]> objects = query.getResultList();
        for(int i=0; i< objects.size();i++){
            Object[] objArray = objects.get(i);
            Optional<AuditEnversInfo> auditEnversInfoOptional = auditEnversInfoRepo.findById((int) objArray[0]);
            if (auditEnversInfoOptional.isPresent()) {
                AuditEnversInfo auditEnversInfo = auditEnversInfoOptional.get();
                WarrantyBillDTO dto = new WarrantyBillDTO((String) objArray[1],  (String) objArray[2], (Date) objArray[3]);
                auditEnversInfo.setRevision(dto);
                audit.add(auditEnversInfo);
            }
        }
        return audit;
    }

    public List<?> getAllRevisions(Date start, Date end) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);

        AuditQuery query = auditReader.createQuery()
                .forRevisionsOfEntity(WarrantyBillEntity.class, true, true)
                .addProjection(AuditEntity.revisionNumber())
                .addProjection(AuditEntity.property("staffId"))
                .addProjection(AuditEntity.property("customer_id"))
                .addProjection(AuditEntity.property("warrantyDate"))
                .addProjection(AuditEntity.property("id"))
                .addProjection(AuditEntity.revisionType())
                .addOrder(AuditEntity.revisionNumber().desc());

        List<AuditEnversInfo> audit = new ArrayList<AuditEnversInfo>();
        List<Object[]> objects = query.getResultList();
        for(int i=0; i< objects.size();i++){
            Object[] objArray = objects.get(i);
            Optional<AuditEnversInfo> auditEnversInfoOptional = auditEnversInfoRepo.findById((int) objArray[0]);
            if (auditEnversInfoOptional.isPresent()) {
                AuditEnversInfo auditEnversInfo = auditEnversInfoOptional.get();
                WarrantyBillDTO dto = new WarrantyBillDTO((String) objArray[1],  (String) objArray[2], (Date) objArray[3], (String) objArray[4]);
                auditEnversInfo.setRevision(dto);
                audit.add(auditEnversInfo);
            }
        }
        List<AuditEnversInfo> auditReturn = new ArrayList<AuditEnversInfo>();
        for(int i=0; i< audit.size();i++){
            if (audit.get(i).getTimestamp() > start.getTime() && audit.get(i).getTimestamp() < end.getTime() ) {
                auditReturn.add(audit.get(i));
            }
        }
        return auditReturn;
    }
}
