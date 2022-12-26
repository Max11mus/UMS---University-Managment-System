package com.foxminded.ums.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@Tag(name = "Home Page", description = "Home Page")
@Validated
@RequestMapping(value = "/home")
public class HomeRestController {

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_TEACHER', 'ROLE_STUDENT')")
    public ResponseEntity<String> homePageGet(Principal principal) {
        if (principal == null) {
            return ResponseEntity.ok().body("University Management System (UMS)");
        } else {
            return ResponseEntity.ok().body("University Management System (UMS) ---   User - " + principal.getName());
        }
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_TEACHER', 'ROLE_STUDENT')")
    public ResponseEntity<String> homePagePost(Principal principal) {
        if (principal == null) {
            return ResponseEntity.ok().body("University Management System (UMS)");
        } else {
            return ResponseEntity.ok().body("University Management System (UMS) ---   User - " + principal.getName());
        }
    }

}
