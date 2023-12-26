package com.penguin.esms.components.staff;

import com.penguin.esms.entity.Error;
import com.penguin.esms.envers.AuditEnversInfoRepo;
import com.penguin.esms.mapper.DTOtoEntityMapper;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StaffService {
    private final EntityManager entityManager;
    private final AuditEnversInfoRepo auditEnversInfoRepo;
    private final StaffRepository staffRepository;
    private final DTOtoEntityMapper mapper;

//    public void getStaffProfile(Principal connectedUser) {
//        StaffEntity staff = (StaffEntity) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
//    }

    public List<StaffEntity> findByName(String name) {
        return staffRepository.findByNameContainingIgnoreCaseAndIsStopped(name, false);
    }

//    public List<StaffEntity> findResigned(String name) {
//        return staffRepository.findByNameContainingIgnoreCaseAndIsStopped(name, true);
//    }
  
//    public StaffEntity getOne(String id) {
//        Optional<StaffEntity> staff = staffRepository.findById(id);
//        if (staff.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, new Error("Staff not existed").toString());
//        }
//        if (staff.get().getIsStopped() == true)
//            throw new ResponseStatusException(
//                    HttpStatus.BAD_REQUEST, new Error("Staff has resigned").toString());
//        return staff.get();
//    }

    public StaffEntity update(StaffDTO staffDTO, String id) {
//        if (staffRepository.findById(id).isEmpty())
//            throw new ResponseStatusException(
//                    HttpStatus.NOT_FOUND, new Error("Staff not existed").toString());
        StaffEntity staff = staffRepository.findById(id).get();
        mapper.updateStaffFromDto(staffDTO, staff);
        return staffRepository.save(staff);
    }

    public void remove(String id) {
        Optional<StaffEntity> staff = staffRepository.findById(id);
        if (staff.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, new Error("Staff not existed").toString());
        if (staff.get().getIsStopped() == true)
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, new Error("Staff has resigned").toString());
        staff.get().setIsStopped(true);
        staffRepository.save(staff.get());
    }

//    public List<?> getRevisionsForStaff(String id) {
//        Optional<StaffEntity> staffOp = staffRepository.findById(id);
//        if (staffOp.isEmpty())
//            throw new ResponseStatusException(
//                    HttpStatus.NOT_FOUND, new Error("Staff not existed").toString());
//        AuditReader auditReader = AuditReaderFactory.get(entityManager);
//
//        AuditQuery query = auditReader.createQuery()
//                .forRevisionsOfEntity(StaffEntity.class, true, true)
//                .add(AuditEntity.id().eq(id))
//                .addProjection(AuditEntity.revisionNumber())
//                .addProjection(AuditEntity.property("id"))
//                .addProjection(AuditEntity.property("name"))
//                .addProjection(AuditEntity.property("phone"))
//                .addProjection(AuditEntity.property("email"))
//                .addProjection(AuditEntity.property("citizenId"))
//                .addProjection(AuditEntity.property("role"))
//                .addProjection(AuditEntity.revisionType())
//                .addOrder(AuditEntity.revisionNumber().desc());
//
//        List<AuditEnversInfo> staffAudit = new ArrayList<AuditEnversInfo>();
//        List<Object[]> objects = query.getResultList();
//        for(int i=0; i< objects.size();i++){
//            Object[] objArray = objects.get(i);
//            Optional<AuditEnversInfo> auditEnversInfoOptional = auditEnversInfoRepo.findById((int) objArray[0]);
//            if (auditEnversInfoOptional.isPresent()) {
//                AuditEnversInfo auditEnversInfo = auditEnversInfoOptional.get();
//                StaffDTO staff = new StaffDTO(id, (String) objArray[2],  (String) objArray[3], (String) objArray[4], (String) objArray[5], (Role) objArray[6]);
//                auditEnversInfo.setRevision(staff);
//                staffAudit.add(auditEnversInfo);
//            }
//        }
//        return staffAudit;
//    }
}
