package com.foxminded.ums.exeptions;

public class StudentIlegalUuidException extends RuntimeException {
    public StudentIlegalUuidException(String message) {
        super(message);
    }

    public StudentIlegalUuidException(String message, Throwable cause) {
        super(message, cause);
    }

}
