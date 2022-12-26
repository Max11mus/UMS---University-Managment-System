package com.foxminded.ums.exeptions;

import java.util.UUID;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(UUID uuid, Throwable cause) {
        super("Student with ID: " + uuid + " not found", cause);
    }

}
