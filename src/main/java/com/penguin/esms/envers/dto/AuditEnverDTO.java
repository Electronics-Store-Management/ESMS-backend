package com.penguin.esms.envers.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.RevisionType;

@Getter
@Setter
@NoArgsConstructor
public class AuditEnverDTO {
    private String id;
    private String username;
    private RevisionType timestamp;

    public AuditEnverDTO(String id, String username, RevisionType timestamp) {
        this.id = id;
        this.username = username;
        this.timestamp = timestamp;
    }
}
