package com.penguin.esms.components.supplier;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.penguin.esms.components.product.ProductEntity;
import com.penguin.esms.entity.BaseEntity;
import com.penguin.esms.entity.NoteEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SupplierEntity extends NoteEntity {
    private String name;
    private String phone;
    private String email;
    private String address;

    @JsonIgnoreProperties(value = {"suppliers"})
    @ManyToMany(mappedBy = "suppliers")
    private List<ProductEntity> products;
}
