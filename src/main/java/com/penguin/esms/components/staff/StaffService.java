package com.penguin.esms.components.staff;

import com.google.api.services.gmail.Gmail;
import com.penguin.esms.components.customer.CustomerEntity;
import com.penguin.esms.components.customer.dto.CustomerDTO;
import com.penguin.esms.components.product.ProductEntity;
import com.penguin.esms.components.product.dto.ProductDTO;
import com.penguin.esms.components.supplier.SupplierEntity;
import com.penguin.esms.entity.Error;
import com.penguin.esms.envers.AuditEnversInfo;
import com.penguin.esms.envers.AuditEnversInfoRepo;
import com.penguin.esms.mapper.DTOtoEntityMapper;
import com.penguin.esms.services.EmailRequestService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;


    public void getStaffProfile(Principal connectedUser) {
        StaffEntity staff = (StaffEntity) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
    }

    public StaffEntity addStaff(StaffDTO dto) throws Exception {
        Optional<StaffEntity> optional = staffRepository.findByCitizenId(dto.getCitizenId());
        if (optional.isPresent()) {
            if (optional.get().getIsStopped() == true)
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, new Error("Staff has resigned").toString());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, new Error("Staff has been existed").toString());
        }
        StaffEntity staff = updateFromDTO(dto, new StaffEntity());
        staff.setIsStopped(false);
        staff.setPassword(passwordEncoder.encode(random()));
        System.out.println(staff.getEmail());
        new EmailRequestService().sendMail("ESMS Account email verification needed", staff.getEmail(), String.format("""
                Hi %s,
                Welcome to ESMS. 
                Here is your password to login ESMS system :
                             %s   
                Best regard,
                ESMS
                """, staff.getName(), staff.getPassword()));
        return staffRepository.save(staff);
    }

    public void changePassword(String oldPassword, String newPassword, Principal connectedUser) throws Exception {
        StaffEntity staff = (StaffEntity) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        Optional<StaffEntity> optional = staffRepository.findById(staff.getId());
        if (optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, new Error("Staff has not been existed").toString());
        }
        try {
            if (optional.get().getIsStopped() == true)
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, new Error("Staff has resigned").toString());
        } catch (NullPointerException e) {
        }
        if (!passwordEncoder.matches(oldPassword, optional.get().getPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, new Error("Password not true").toString());
        }
        optional.get().setPassword(passwordEncoder.encode(newPassword));

        new EmailRequestService().sendMail("ESMS Account email verification needed", optional.get().getEmail(), String.format("""
                Hi %s,
                Welcome to ESMS. 
                Here is your new password to login ESMS system :
                             %s   
                Best regard,
                ESMS
                """, optional.get().getName(), optional.get().getPassword()));
        staffRepository.save(optional.get());
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

    public StaffEntity findByMail(String email) {
        return staffRepository.findByEmail(email).get();
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
        for (int i = 0; i < objects.size(); i++) {
            Object[] objArray = objects.get(i);
            Optional<AuditEnversInfo> auditEnversInfoOptional = auditEnversInfoRepo.findById((int) objArray[0]);
            if (auditEnversInfoOptional.isPresent()) {
                AuditEnversInfo auditEnversInfo = auditEnversInfoOptional.get();
                StaffDTO staff = new StaffDTO(id, (String) objArray[2], (String) objArray[3], (String) objArray[4], (String) objArray[5], (Role) objArray[6]);
                auditEnversInfo.setRevision(staff);
                staffAudit.add(auditEnversInfo);
            }
        }
        return staffAudit;
    }
}
