package com.penguin.esms.entity;

import com.fasterxml.jackson.annotation.*;
import com.penguin.esms.components.staff.StaffEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.UUID;
@MappedSuperclass
@Getter
@Setter
//@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Boolean isStopped = false;

//    @Column(name = "createddate")
//    @CreatedDate
//    private Date createdDate;

//    @Column(name = "modifieddate")
//    @LastModifiedDate
//    private Date modifiedDate;

//    @CreatedBy
//    @ManyToOne
//    @JoinColumn(name="created_by")
//    private StaffEntity createdBy;
//
//    @LastModifiedBy
//    @ManyToOne
//    @JoinColumn(name="modified_by")
//    private StaffEntity modifiedBy;


}
