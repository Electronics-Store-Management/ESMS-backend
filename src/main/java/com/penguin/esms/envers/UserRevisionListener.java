package com.penguin.esms.envers;

import com.penguin.esms.components.staff.StaffEntity;
import org.hibernate.envers.RevisionListener;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class UserRevisionListener implements RevisionListener {
    @Override
    public void newRevision(Object revisionEntity) {
        AuditEnversInfo auditEnversInfo = (AuditEnversInfo) revisionEntity;
        auditEnversInfo.setUsername(getUsername());
    }
    private String getUsername(){
        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();
        if (authentication == null ||
                !authentication.isAuthenticated() ||
                authentication instanceof AnonymousAuthenticationToken
        ) {
            return null;
        }

        StaffEntity userPrincipal = (StaffEntity) authentication.getPrincipal();
        return userPrincipal.getUsername();
    }
}
