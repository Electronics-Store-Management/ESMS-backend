package com.penguin.esms.components.staff;

import com.penguin.esms.entity.NoteEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table
public class StaffEntity extends NoteEntity {
    private String name;
    private String phone;
    private String username;
    private String password;
    private String role;
    private String email;
    private String citizenId;

    public StaffEntity(String note) {
        super(note);
    }
}
