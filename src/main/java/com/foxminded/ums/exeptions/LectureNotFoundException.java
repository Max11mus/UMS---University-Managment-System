package com.foxminded.ums.exeptions;

import java.util.UUID;

public class LectureNotFoundException extends RuntimeException {
    public LectureNotFoundException(UUID uuid, Throwable cause) {
        super("Lecture with ID: " + uuid + " not found", cause);
    }

}
