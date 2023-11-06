package com.penguin.esms.components.category;

import com.penguin.esms.components.product.ProductEntity;
import com.penguin.esms.components.staff.StaffEntity;
import com.penguin.esms.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter
@RequiredArgsConstructor
public class CategoryEntity extends BaseEntity {
    private String name;
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ProductEntity> products;

    @Override
    public String toString() {
        return "CategoryEntity{" +
                "name='" + name + '\'' +
                '}';
    }
}
