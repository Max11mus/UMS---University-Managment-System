package com.foxminded.ums.exeptions;

import java.util.UUID;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(UUID uuid, Throwable cause) {
        super(uuid + " isn't correct Lecture UUID. See RFC 4122 - 4.1. Format", cause);
    }

}
