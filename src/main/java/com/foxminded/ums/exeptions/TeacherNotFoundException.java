package com.foxminded.ums.exeptions;

public class TeacherNotFoundException extends RuntimeException {
    public TeacherNotFoundException(String message) {
        super(message);
    }

    public TeacherNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
