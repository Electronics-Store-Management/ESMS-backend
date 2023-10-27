package com.penguin.esms.components.permission;

import com.penguin.esms.entity.NoteEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table
public class PermissionEntity extends NoteEntity {
    private String name;
    private String phone;
    private String username;
    private String password;
    private String role;
    private String email;
    private String citizenId;

    public PermissionEntity(String note, String name, String phone, String username, String password, String role, String email, String citizenId) {
        super(note);
        this.name = name;
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
        this.citizenId = citizenId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(String citizenId) {
        this.citizenId = citizenId;
    }

    @Override
    public String toString() {
        return "PermissionEntity{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                ", citizenId='" + citizenId + '\'' +
                '}';
    }
}
