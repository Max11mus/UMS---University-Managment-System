package com.foxminded.ums.exeptions;

import java.util.UUID;

public class TeacherNotFoundException extends RuntimeException {
    public TeacherNotFoundException(UUID uuid, Throwable cause) {
        super("Teacher with ID: " + uuid + " not found", cause);
    }

}
