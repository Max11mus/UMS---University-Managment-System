package com.foxminded.ums.exeptions;

public class TeacherIlegalUuidException extends RuntimeException {
    public TeacherIlegalUuidException(String message) {
        super(message);
    }

    public TeacherIlegalUuidException(String message, Throwable cause) {
        super(message, cause);
    }

}
