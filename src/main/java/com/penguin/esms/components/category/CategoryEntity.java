package com.penguin.esms.components.category;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.penguin.esms.components.product.ProductEntity;
import com.penguin.esms.components.staff.StaffEntity;
import com.penguin.esms.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter
@RequiredArgsConstructor

public class CategoryEntity extends BaseEntity {
    @Audited
    private String name;
    @JsonIgnoreProperties(value = {"category"})
    @NotAudited
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private List<ProductEntity> products;

    @Override
    public String toString() {
        return "CategoryEntity{" +
                "name='" + name + '\'' +
                '}';
    }
}
