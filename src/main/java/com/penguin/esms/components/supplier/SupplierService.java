package com.penguin.esms.components.supplier;

import com.penguin.esms.components.category.CategoryEntity;
import com.penguin.esms.components.customer.CustomerEntity;
import com.penguin.esms.components.customer.dto.CustomerDTO;
import com.penguin.esms.components.saleBill.SaleBillEntity;
import com.penguin.esms.components.saleBill.dto.SaleBillDTO;
import com.penguin.esms.components.supplier.dto.SupplierDTO;
import com.penguin.esms.entity.Error;
import com.penguin.esms.envers.AuditEnversInfo;
import com.penguin.esms.envers.AuditEnversInfoRepo;
import com.penguin.esms.mapper.DTOtoEntityMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SupplierService {
    @PersistenceContext
    private final EntityManager entityManager;
    private final AuditEnversInfoRepo auditEnversInfoRepo;
    private final SupplierRepo supplierRepo;
    private final DTOtoEntityMapper mapper;

    public List<SupplierEntity> findByName(String name) {
        return supplierRepo.findByNameContainingIgnoreCaseAndIsStopped(name, false);
    }

    public List<SupplierEntity> findTermination(String name) {
        return supplierRepo.findByNameContainingIgnoreCaseAndIsStopped(name, true);
    }

    public SupplierEntity getOne(String id) {
        Optional<SupplierEntity> optionalSupplier = supplierRepo.findById(id);
        if (optionalSupplier.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, new Error("Supplier not found").toString());
        }
        if (optionalSupplier.get().getIsStopped() == true)
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Supplier has terminated cooperation");
        return optionalSupplier.get();
    }

    public SupplierEntity add(SupplierDTO dto) {
        Optional<SupplierEntity> supplierEntityOptional = supplierRepo.findByPhone(dto.getPhone());
        if (supplierEntityOptional.isPresent())
            if (supplierEntityOptional.get().getIsStopped() == true)
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, new Error("Supplier has terminated cooperation ").toString());
        SupplierEntity supplier = updateFromDTO(dto, new SupplierEntity());
        supplier.setIsStopped(false);
        return supplierRepo.save(supplier);
    }

    private SupplierEntity updateFromDTO(SupplierDTO dto, SupplierEntity entity) {
        mapper.updateSupplierFromDto(dto, entity);
        return entity;
    }
    public SupplierEntity update(SupplierDTO supplierDTO, String id) {
        Optional<SupplierEntity> optionalSupplier = supplierRepo.findById(id);
        if (optionalSupplier.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, new Error("Supplier not found").toString());
        }
        SupplierEntity supplier = optionalSupplier.get();
        mapper.updateSupplierFromDto(supplierDTO, supplier);
        if (supplierDTO.getNote() != null) supplier.setNote(supplierDTO.getNote());
        return supplierRepo.save(supplier);
    }

    public void remove(String id) {
        Optional<SupplierEntity> supplierEntityOptional = supplierRepo.findById(id);
        if (supplierEntityOptional.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, new Error("Supplier not found").toString());
        if (supplierEntityOptional.get().getIsStopped() == true)
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, new Error("Supplier has terminated cooperation ").toString());
        supplierEntityOptional.get().setIsStopped(true);
        supplierRepo.save(supplierEntityOptional.get());
    }

    @Transactional
    public List<?> getRevisions(String id) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);

        AuditQuery query = auditReader.createQuery()
                .forRevisionsOfEntity(SupplierEntity.class, true, true)
                .add(AuditEntity.id().eq(id))
                .addProjection(AuditEntity.revisionNumber())
                .addProjection(AuditEntity.property("name"))
                .addProjection(AuditEntity.property("phone"))
                .addProjection(AuditEntity.property("email"))
                .addProjection(AuditEntity.property("address"))
                .addProjection(AuditEntity.revisionType())
                .addOrder(AuditEntity.revisionNumber().desc());

        List<AuditEnversInfo> audit = new ArrayList<AuditEnversInfo>();
        List<Object[]> objects = query.getResultList();
        for(int i=0; i< objects.size();i++){
            Object[] objArray = objects.get(i);
            Optional<AuditEnversInfo> auditEnversInfoOptional = auditEnversInfoRepo.findById((int) objArray[0]);
            if (auditEnversInfoOptional.isPresent()) {
                AuditEnversInfo auditEnversInfo = auditEnversInfoOptional.get();
                SupplierDTO dto = new SupplierDTO((String) objArray[1],  (String) objArray[2], (String) objArray[3], (String) objArray[4]);
                auditEnversInfo.setRevision(dto);
                audit.add(auditEnversInfo);
            }
        }
        entityManager.close();
        return audit;
    }
}
