package com.foxminded.ums.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SecurityHelper {
    public boolean isAdmin(Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        if (authentication != null &&
                authentication.getAuthorities()
                        .stream()
                        .anyMatch(a -> a.getAuthority().equals(Role.ROLE_ADMIN.toString()))) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isTeacher(Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        if (authentication != null &&
                authentication.getAuthorities()
                        .stream()
                        .anyMatch(a -> a.getAuthority().equals(Role.ROLE_TEACHER.toString()))) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isStudent(Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        if (authentication != null &&
                authentication.getAuthorities()
                        .stream()
                        .anyMatch(a -> a.getAuthority().equals(Role.ROLE_STUDENT.toString()))) {
            return true;
        } else {
            return false;
        }
    }

}

