package com.penguin.esms.utils;

import com.penguin.esms.security.CustomStaffDetail;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {
    public long getUserId() {
        return ((CustomStaffDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }
}
