package com.penguin.esms.components.warrantyBill;

import com.penguin.esms.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table
@Getter
@Setter
@RequiredArgsConstructor
public class WarrantyBillEntity extends BaseEntity {
    private String staffId;
    private String customerId;
    private Date warrantyDate;

}
