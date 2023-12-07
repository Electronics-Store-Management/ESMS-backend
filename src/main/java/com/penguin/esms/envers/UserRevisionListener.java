package com.penguin.esms.envers;

import com.penguin.esms.components.staff.StaffEntity;
import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserRevisionListener implements RevisionListener {
    @Override
    public void newRevision(Object revisionEntity) {
        AuditEnversInfo auditEnversInfo = (AuditEnversInfo) revisionEntity;
        auditEnversInfo.setUsername(getUsername());
    }
    private String getUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
//            if (principal instanceof StaffEntity) {
                return ((StaffEntity) principal).getUsername();
//            } else {
//                return "unknown_user";
//            }
//
//        return "unknown_user";
    }
}
