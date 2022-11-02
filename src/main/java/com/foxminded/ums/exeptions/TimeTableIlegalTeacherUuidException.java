package com.foxminded.ums.exeptions;

public class TimeTableIlegalTeacherUuidException extends RuntimeException {
    public TimeTableIlegalTeacherUuidException(String message) {
        super(message);
    }

    public TimeTableIlegalTeacherUuidException(String message, Throwable cause) {
        super(message, cause);
    }

}
