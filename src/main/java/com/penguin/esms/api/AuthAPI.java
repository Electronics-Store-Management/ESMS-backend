package com.penguin.esms.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "auth")
public class AuthAPI {
    @GetMapping
    public ResponseEntity<?> ping() {
        return ResponseEntity.ok("OKEE");
    }
}
