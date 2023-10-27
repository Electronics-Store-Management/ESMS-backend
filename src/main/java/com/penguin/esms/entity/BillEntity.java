package com.penguin.esms.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BillEntity extends NoteEntity{
    public BillEntity(String note) {
        super(note);
    }
}
