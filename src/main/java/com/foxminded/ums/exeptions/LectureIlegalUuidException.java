package com.foxminded.ums.exeptions;

public class LectureIlegalUuidException extends RuntimeException {
    public LectureIlegalUuidException(String message) {
        super(message);
    }

    public LectureIlegalUuidException(String message, Throwable cause) {
        super(message, cause);
    }

}
