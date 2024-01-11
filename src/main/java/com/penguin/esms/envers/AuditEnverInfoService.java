package com.penguin.esms.envers;

import com.penguin.esms.components.importBill.ImportBillService;
import com.penguin.esms.components.saleBill.SaleBillService;
import com.penguin.esms.components.staff.StaffEntity;
import com.penguin.esms.components.staff.StaffRepository;
import com.penguin.esms.components.warrantyBill.WarrantyBillService;
import com.penguin.esms.entity.Error;
import com.penguin.esms.envers.dto.AuditEnverDTO;
import com.penguin.esms.envers.dto.AuditEnverInfoDTO;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Setter
@Getter
public class AuditEnverInfoService {
    private final AuditEnversInfoRepo auditEnversInfoRepo;
    private final StaffRepository staffRepo;
    private final EntityManager entityManager;

    private final ImportBillService importBillService;
    private final SaleBillService saleBillService;
    private final WarrantyBillService warrantyBillService;

    public List<AuditEnversInfo> getRecord(String username) {
        if (username != null && !username.isEmpty()) {
            Optional<StaffEntity> staff = staffRepo.findByEmail(username);
            if (staff.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, new Error("staff not found").toString());
            }
        }
        List<AuditEnversInfo> audit = new ArrayList<AuditEnversInfo>();
        audit = auditEnversInfoRepo.findByUsername(username);
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
        for (String t : typeEntity) {
            Class<?> type = Class.forName(t);
            AuditQuery query = auditReader.createQuery()
                    .forRevisionsOfEntity(type, true, true)
                    .add(AuditEntity.revisionNumber().in((Collection) audit.stream().map(auditEnversInfo -> auditEnversInfo.getId()).collect(Collectors.toList())))
                    .addProjection(AuditEntity.revisionNumber())
                    .addProjection(AuditEntity.property("id"))
                    .addProjection(AuditEntity.revisionType())
                    .addOrder(AuditEntity.revisionNumber().desc());

//            auditByStaff.addAll(query.getResultList());
            List<Object[]> objects = query.getResultList();
            for (int i = 0; i < objects.size(); i++) {
                Object[] objArray = objects.get(i);
                Optional<AuditEnversInfo> auditEnversInfoOptional = auditEnversInfoRepo.findById((int) objArray[0]);
                if (auditEnversInfoOptional.isPresent()) {
                    AuditEnversInfo auditEnversInfo = auditEnversInfoOptional.get();
                    AuditEnverInfoDTO dto = new AuditEnverInfoDTO((String) objArray[1], t, (RevisionType) objArray[2], (Integer) objArray[0]);
                    dto.setName(t);
                    auditEnversInfo.setRevision(dto);
                    auditByStaff.add(auditEnversInfo);
                }
            }
        }
        return auditByStaff;
    }

    //    public AuditEnverDTO randomData(Principal connectedUser) {
//        String numbers = "123456789";
//        StaffEntity staff = (StaffEntity) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
//
//        importBillService.postImportBill(importBillService.random(), staff);
//        saleBillService.post(saleBillService.random(), staff);
//        warrantyBillService.postWarrantyBill(warrantyBillService.random(), staff);
//
//        List<?> importRev = importBillService.getAll();
//        List<?> saleRev = saleBillService.getAll();
//        List<?> warrantyRev = warrantyBillService.getAll();
//
//        for(int i=0; i<=Integer.valueOf(Random.random(1,numbers));i++){
//
////            AuditEnverInfoDTO auditEnverInfoDTO = random();
//            CustomerEntity customerEntity = customerService.postCustomer(customerDTO);
//            String customerId = customerEntity.getId();
//
//            warrantyProductDTOS.add(warrantyProductService.random());
//        }
//        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
//        String bool = "01";
//        String all = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
//        String name = Random.random(10, characters);
//        RevisionType revType = RevisionType.valueOf(Random.random(1, bool));
//        Integer revNumber = Integer.valueOf(Random.random(Integer.valueOf(Random.random(1,numbers)), numbers));
//        return new AuditEnverInfoDTO(name , revType, revNumber);
//    }
    public void randomData() {
        List<AuditEnversInfo> auditEnversInfoList = auditEnversInfoRepo.findAll();
        Integer length = auditEnversInfoList.size();

        for (int i = 0; i < length; i++) {
            long timeStamp = auditEnversInfoList.get(i).getTimestamp();
            long customTime = timeStamp - (long) (length - i) * 5 * 60 * 60 * 1000;
            auditEnversInfoList.get(i).setTimestamp(customTime);

            auditEnversInfoRepo.save(auditEnversInfoList.get(i));
        }


    }

}
