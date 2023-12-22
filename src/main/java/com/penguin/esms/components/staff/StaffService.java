package com.penguin.esms.components.staff;

import com.penguin.esms.components.customer.CustomerEntity;
import com.penguin.esms.components.customer.dto.CustomerDTO;
import com.penguin.esms.components.product.ProductEntity;
import com.penguin.esms.components.product.dto.ProductDTO;
import com.penguin.esms.components.supplier.SupplierEntity;
import com.penguin.esms.entity.Error;
import com.penguin.esms.envers.AuditEnversInfo;
import com.penguin.esms.envers.AuditEnversInfoRepo;
import com.penguin.esms.mapper.DTOtoEntityMapper;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.penguin.esms.utils.Random.random;

@Service
@RequiredArgsConstructor
public class StaffService {
    private final EntityManager entityManager;
    private final AuditEnversInfoRepo auditEnversInfoRepo;
    private final StaffRepository staffRepository;
    private final DTOtoEntityMapper mapper;

    public void getStaffProfile(Principal connectedUser) {
        StaffEntity staff = (StaffEntity) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
    }

    public StaffEntity addStaff(StaffDTO dto) {
        Optional<StaffEntity> optional = staffRepository.findByCitizenId(dto.getCitizenId());
        if (optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, new Error("Staff not existed").toString());
        }
        if (optional.get().getIsStopped() == true)
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, new Error("Staff has resigned").toString());
        StaffEntity staff = updateFromDTO(dto, new StaffEntity());
        staff.setIsStopped(false);
        optional.get().setPassword(random());
//        cc : send pass do email
//        optionsal.setPass(pass)
        return staffRepository.save(staff);
    }

    public void changePassword(String oldPassword, String newPassword, String staffId){
        Optional<StaffEntity> optional = staffRepository.findById(staffId);
        try {
            if (optional.get().getPassword().equals(oldPassword)){
                optional.get().setPassword(newPassword);
            }
        } catch(NullPointerException s){}
    }
    private StaffEntity updateFromDTO(StaffDTO dto, StaffEntity staff) {
        mapper.updateStaffFromDto(dto, staff);
        return staff;
    }

    public StaffEntity update(StaffDTO dto, String id) throws IOException {
        Optional<StaffEntity> optional = staffRepository.findByCitizenId(dto.getCitizenId());
        if (optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, new Error("Staff not existed").toString());
        }
        if (optional.get().getIsStopped() == true)
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, new Error("Staff has resigned").toString());
        StaffEntity staffEntity = updateFromDTO(dto, staffRepository.findById(id).get());
        return staffRepository.save(staffEntity);
    }

    public List<StaffEntity> findByName(String name) {
        return staffRepository.findByNameContainingIgnoreCaseAndIsStopped(name, false);
    }

    public List<StaffEntity> findResigned(String name) {
        return staffRepository.findByNameContainingIgnoreCaseAndIsStopped(name, true);
    }
  
    public StaffEntity getOne(String id) {
        Optional<StaffEntity> staff = staffRepository.findById(id);
        if (staff.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, new Error("Staff not existed").toString());
        }
        if (staff.get().getIsStopped() == true)
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, new Error("Staff has resigned").toString());
        return staff.get();
    }

//    public StaffEntity update(StaffDTO staffDTO, String id) {
//        if (staffRepository.findById(id).isEmpty())
//            throw new ResponseStatusException(
//                    HttpStatus.NOT_FOUND, new Error("Staff not existed").toString());
//        StaffEntity staff = staffRepository.findById(id).get();
//        mapper.updateStaffFromDto(staffDTO, staff);
//        return staffRepository.save(staff);
//    }

    public void remove(String id) {
        Optional<StaffEntity> staff = staffRepository.findById(id);
        if (staff.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, new Error("Staff not existed").toString());
        staff.get().setIsStopped(true);
        staffRepository.save(staff.get());
    }

    public List<?> getRevisionsForStaff(String id) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);

        AuditQuery query = auditReader.createQuery()
                .forRevisionsOfEntity(StaffEntity.class, true, true)
                .add(AuditEntity.id().eq(id))
                .addProjection(AuditEntity.revisionNumber())
                .addProjection(AuditEntity.property("id"))
                .addProjection(AuditEntity.property("name"))
                .addProjection(AuditEntity.property("phone"))
                .addProjection(AuditEntity.property("email"))
                .addProjection(AuditEntity.property("citizenId"))
                .addProjection(AuditEntity.property("role"))
                .addProjection(AuditEntity.revisionType())
                .addOrder(AuditEntity.revisionNumber().desc());

        List<AuditEnversInfo> staffAudit = new ArrayList<AuditEnversInfo>();
        List<Object[]> objects = query.getResultList();
        for(int i=0; i< objects.size();i++){
            Object[] objArray = objects.get(i);
            Optional<AuditEnversInfo> auditEnversInfoOptional = auditEnversInfoRepo.findById((int) objArray[0]);
            if (auditEnversInfoOptional.isPresent()) {
                AuditEnversInfo auditEnversInfo = auditEnversInfoOptional.get();
                StaffDTO staff = new StaffDTO(id, (String) objArray[2],  (String) objArray[3], (String) objArray[4], (String) objArray[5], (Role) objArray[6]);
                auditEnversInfo.setRevision(staff);
                staffAudit.add(auditEnversInfo);
            }
        }
        return staffAudit;
    }
}
