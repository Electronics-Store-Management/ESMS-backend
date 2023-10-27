package com.penguin.esms.components.staff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "staff")
public class StaffController {
    private final StaffService staffService;
    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }
}
