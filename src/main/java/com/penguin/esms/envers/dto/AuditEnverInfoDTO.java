package com.penguin.esms.envers.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.RevisionType;

@Getter
@Setter
@NoArgsConstructor
public class AuditEnverInfoDTO {
    private String id;
    private String name;
    private RevisionType revType;
    private Integer revNumber;

    public AuditEnverInfoDTO(String id, String name, RevisionType revType, Integer revNumber) {
        this.id = id;
        this.name = name;
        this.revType = revType;
        this.revNumber = revNumber;
    }
}
