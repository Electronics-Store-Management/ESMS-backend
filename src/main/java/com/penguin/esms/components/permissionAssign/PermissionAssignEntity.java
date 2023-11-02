package com.penguin.esms.components.permissionAssign;

import com.penguin.esms.entity.BaseEntity;
import com.penguin.esms.entity.NoteEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table
public class PermissionAssignEntity extends NoteEntity {
    private String staffId;
    private String permissionId;
    private String itemId;
    private String type;

    public PermissionAssignEntity(String note, String staffId, String permissionId, String itemId, String type) {
        super(note);
        this.staffId = staffId;
        this.permissionId = permissionId;
        this.itemId = itemId;
        this.type = type;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "PermissionAssignEntity{" +
                "staffId='" + staffId + '\'' +
                ", permissionId='" + permissionId + '\'' +
                ", itemId='" + itemId + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
