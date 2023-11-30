package com.penguin.esms.components.warrantyProduct;

import com.penguin.esms.entity.NoteEntity;
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
public class WarrantyProductEntity extends NoteEntity {
    private String staffId;
    private String warrantyBillId;

    private String productId;
    private Integer quantity;
    private String warrantyContent;

}
