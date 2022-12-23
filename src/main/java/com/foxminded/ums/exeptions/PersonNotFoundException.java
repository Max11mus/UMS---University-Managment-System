package com.foxminded.ums.exeptions;

import java.util.UUID;

public class PersonNotFoundException extends Throwable {
    public PersonNotFoundException(UUID uuid, Throwable cause) {
        super("Person with ID: " + uuid + " not found", cause);
    }
}
