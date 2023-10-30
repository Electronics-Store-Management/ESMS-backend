package com.penguin.esms.repository;

import com.penguin.esms.components.staff.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffRepository extends JpaRepository<StaffEntity, Long> {
    StaffEntity findOneByEmailAndStatus(String username, int status);
//    StaffEntity findOneByUserNameAndTypeAndStatus(String username, int type, int status);
//    List<StaffEntity> findByDisplayNameContainingOrEmailContaining(String name, String email);
}
