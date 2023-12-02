package com.penguin.esms.components.importBill;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.penguin.esms.components.importProduct.ImportProductEntity;
import com.penguin.esms.entity.NoteEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@RequiredArgsConstructor
public class ImportBillEntity extends NoteEntity {
    private String staffId;
    private String supplierId;
    private String paymentMethod;

    @JsonIgnoreProperties(value = {"import_bill"})
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "importBill")
    private List<ImportProductEntity> importProducts;
}
