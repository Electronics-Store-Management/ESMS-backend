package com.penguin.esms.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BillEntity extends NoteEntity{
    public BillEntity(String note) {
        super(note);
    }
}
