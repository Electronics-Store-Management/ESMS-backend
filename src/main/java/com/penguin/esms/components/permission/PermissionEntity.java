package com.penguin.esms.components.permission;

import com.penguin.esms.entity.BaseEntity;
import com.penguin.esms.entity.NoteEntity;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class PermissionEntity extends BaseEntity {
    private String name;
    private String description;

    public PermissionEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "PermissionEntity{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
