package com.foxminded.ums.exeptions;

public class TimeTableUnitIlegalStudentUuidException extends RuntimeException {
    public TimeTableUnitIlegalStudentUuidException(String message) {
        super(message);
    }

    public TimeTableUnitIlegalStudentUuidException(String message, Throwable cause) {
        super(message, cause);
    }

}
