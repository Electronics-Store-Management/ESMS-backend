package com.penguin.esms.components.supplier;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.penguin.esms.components.product.ProductEntity;
import com.penguin.esms.components.staff.validators.PhoneNumberFormat;
import com.penguin.esms.entity.BaseEntity;
import com.penguin.esms.entity.NoteEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import java.util.List;

@Entity
@Table
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Audited
public class SupplierEntity extends NoteEntity {
    @NotNull(message = "name is required")

    private String name;
    @PhoneNumberFormat(message = "Invalid phone number")

    private String phone;
    private String email;
    private String address;

    @NotAudited
    @JsonIgnoreProperties(value = {"suppliers"})
    @ManyToMany(mappedBy = "suppliers")
    @JsonIgnore
    private List<ProductEntity> products;
}
