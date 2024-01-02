package com.penguin.esms.components.staff;

import com.penguin.esms.components.staff.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface StaffRepository extends JpaRepository<StaffEntity, String> {
    List<StaffEntity> findByNameContainingIgnoreCase(String name);
    Optional<StaffEntity> findByEmail(String email);
    Optional<StaffEntity> findByName(String name);
    public List<StaffEntity> findByNameContainingIgnoreCaseAndIsStopped(String name, boolean isStopped);
    Optional<StaffEntity> findById(String id);
    Optional<StaffEntity> findByCitizenId(String citizenId);

}
