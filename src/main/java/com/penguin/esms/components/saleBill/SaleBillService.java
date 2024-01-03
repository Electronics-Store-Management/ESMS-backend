package com.penguin.esms.components.saleBill;

import com.penguin.esms.components.customer.CustomerEntity;
import com.penguin.esms.components.customer.CustomerRepo;
import com.penguin.esms.components.customer.CustomerService;
import com.penguin.esms.components.customer.dto.CustomerDTO;
import com.penguin.esms.components.importBill.ImportBillEntity;
import com.penguin.esms.components.importBill.dto.ImportBillDTO;
import com.penguin.esms.components.importProduct.ImportProductEntity;
import com.penguin.esms.components.importProduct.dto.ImportProductDTO;
import com.penguin.esms.components.product.ProductEntity;
import com.penguin.esms.components.product.ProductRepo;
import com.penguin.esms.components.saleBill.dto.SaleBillDTO;
import com.penguin.esms.components.saleProduct.SaleProductEntity;
import com.penguin.esms.components.saleProduct.SaleProductRepo;
import com.penguin.esms.components.saleProduct.dto.SaleProductDTO;
import com.penguin.esms.components.staff.StaffEntity;
import com.penguin.esms.components.warrantyBill.WarrantyBillEntity;
import com.penguin.esms.components.warrantyBill.dto.WarrantyBillDTO;
import com.penguin.esms.entity.Error;
import com.penguin.esms.envers.AuditEnversInfo;
import com.penguin.esms.envers.AuditEnversInfoRepo;
import com.penguin.esms.mapper.DTOtoEntityMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
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
import java.util.*;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class SaleBillService {
    @PersistenceContext
    private final EntityManager entityManager;
    private final AuditEnversInfoRepo auditEnversInfoRepo;
    private final SaleBillRepo saleBillRepo;
    private final SaleProductRepo saleProductRepo;
    private final DTOtoEntityMapper mapper;
    private final ProductRepo productRepo;
    private final CustomerRepo customerRepo;
//
//
//    public SaleBillEntity getSaleBill(String saleBillId) {
//        Optional<SaleBillEntity> saleBill = saleBillRepo.findById(saleBillId);
//        if (saleBill.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sale bill not found");
//        }
//        return saleBill.get();
//    }
    public SaleBillEntity post(SaleBillDTO saleBillDTO, Principal connectedUser) {
        StaffEntity staff = (StaffEntity) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        saleBillDTO.setStaffId(staff.getId());
        List<SaleProductDTO> salePrts = saleBillDTO.getSaleProducts();
        CustomerEntity customer = customerRepo.findById(saleBillDTO.getCustomerId()).get();
        SaleBillEntity sale = updateFromDTO(saleBillDTO, new SaleBillEntity());
        sale.setCustomer(customer);
        saleBillRepo.save(sale);
        for (SaleProductDTO t : salePrts) {
            SaleProductEntity salePrt = updateFromDTO(t, new SaleProductEntity());
            Optional<ProductEntity> product = productRepo.findById(t.getProductId());
            salePrt.setProduct(product.get());
            salePrt.setSaleBill(sale);
            saleProductRepo.save(salePrt);
            List<SaleProductEntity> saleProducts = sale.getSaleProducts();
            saleProducts.add(salePrt);
            sale.setSaleProducts(saleProducts);
        }
        saleBillRepo.save(sale);
        return sale;
    }

    private SaleBillEntity updateFromDTO(SaleBillDTO saleBillDTO, SaleBillEntity sale) {
        mapper.updateSaleBillFromDto(saleBillDTO, sale);
        return sale;
    }

    private SaleProductEntity updateFromDTO(SaleProductDTO dto, SaleProductEntity entity) {
        mapper.updateSaleProductFromDto(dto, entity);
        return entity;
    }
    @Transactional
    public List<?> getRevisions(String id) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);

        AuditQuery query = auditReader.createQuery()
                .forRevisionsOfEntity(SaleBillEntity.class, true, true)
                .add(AuditEntity.id().eq(id))
                .addProjection(AuditEntity.revisionNumber())
                .addProjection(AuditEntity.property("staffId"))
                .addProjection(AuditEntity.property("customer_id"))
                .addProjection(AuditEntity.property("paymentMethod"))
                .addProjection(AuditEntity.property("discount"))
                .addProjection(AuditEntity.property("id"))
                .addProjection(AuditEntity.revisionType())
                .addOrder(AuditEntity.revisionNumber().desc());

        List<AuditEnversInfo> audit = new ArrayList<AuditEnversInfo>();
        List<Object[]> objects = query.getResultList();
        for (int i = 0; i < objects.size(); i++) {
            Object[] objArray = objects.get(i);
            Optional<AuditEnversInfo> auditEnversInfoOptional = auditEnversInfoRepo.findById((int) objArray[0]);
            if (auditEnversInfoOptional.isPresent()) {
                AuditEnversInfo auditEnversInfo = auditEnversInfoOptional.get();
                SaleBillEntity entity = saleBillRepo.findById((String) objArray[5]).get();
                List<SaleProductEntity> saleProducts =  saleProductRepo.findBySaleBillId(entity.getId());
                entity.setSaleProducts(saleProducts);
                auditEnversInfo.setRevision(entity);
                audit.add(auditEnversInfo);
            }
        }
        entityManager.close();
        return audit;
    }
    @Transactional
    public List<?> getAll() {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);

        AuditQuery query = auditReader.createQuery()
                .forRevisionsOfEntity(SaleBillEntity.class, true, true)
                .addProjection(AuditEntity.revisionNumber())
                .addProjection(AuditEntity.property("staffId"))
                .addProjection(AuditEntity.property("customer_id"))
                .addProjection(AuditEntity.property("paymentMethod"))
                .addProjection(AuditEntity.property("discount"))
                .addProjection(AuditEntity.property("id"))
                .addProjection(AuditEntity.revisionType())
                .addOrder(AuditEntity.revisionNumber().desc());

        List<AuditEnversInfo> audit = new ArrayList<AuditEnversInfo>();
        List<Object[]> objects = query.getResultList();
        for (int i = 0; i < objects.size(); i++) {
            Object[] objArray = objects.get(i);
            Optional<AuditEnversInfo> auditEnversInfoOptional = auditEnversInfoRepo.findById((int) objArray[0]);
            if (auditEnversInfoOptional.isPresent()) {
                AuditEnversInfo auditEnversInfo = auditEnversInfoOptional.get();
                SaleBillEntity entity = saleBillRepo.findById((String) objArray[5]).get();
                List<SaleProductEntity> saleProducts = saleProductRepo.findBySaleBillId(entity.getId());
                entity.setSaleProducts(saleProducts);
                auditEnversInfo.setRevision(entity);
                audit.add(auditEnversInfo);
            }
        }
        entityManager.close();
        return audit;
    }
    @Transactional
    public List<?> getAllRevisions(Date start, Date end) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);

        AuditQuery query = auditReader.createQuery()
                .forRevisionsOfEntity(SaleBillEntity.class, true, true)
                .addProjection(AuditEntity.revisionNumber())
                .addProjection(AuditEntity.property("staffId"))
                .addProjection(AuditEntity.property("customer_id"))
                .addProjection(AuditEntity.property("paymentMethod"))
                .addProjection(AuditEntity.property("discount"))
                .addProjection(AuditEntity.property("id"))
                .addProjection(AuditEntity.revisionType())
                .addOrder(AuditEntity.revisionNumber().desc());

        List<AuditEnversInfo> audit = new ArrayList<AuditEnversInfo>();
        List<Object[]> objects = query.getResultList();
        for (int i = 0; i < objects.size(); i++) {
            Object[] objArray = objects.get(i);
            Optional<AuditEnversInfo> auditEnversInfoOptional = auditEnversInfoRepo.findById((int) objArray[0]);
            if (auditEnversInfoOptional.isPresent()) {
                AuditEnversInfo auditEnversInfo = auditEnversInfoOptional.get();
                SaleBillEntity entity = saleBillRepo.findById((String) objArray[5]).get();
                List<SaleProductEntity> saleProducts =  saleProductRepo.findBySaleBillId(entity.getId());
                entity.setSaleProducts(saleProducts);
                auditEnversInfo.setRevision(entity);
                audit.add(auditEnversInfo);
            }
        }
        Map<String, AuditEnversInfo> auditReturn = new HashMap<>();
        for (int i = 0; i < audit.size(); i++) {
            if (audit.get(i).getTimestamp() > start.getTime() && audit.get(i).getTimestamp() < end.getTime()) {
                SaleBillEntity saleBillEntity = (SaleBillEntity) audit.get(i).getRevision();
                auditReturn.put(saleBillEntity.getId(), audit.get(i));
            }
        }
        entityManager.close();
        return Arrays.asList(auditReturn.values().toArray());
    }
}
