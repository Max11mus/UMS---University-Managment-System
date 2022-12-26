package com.foxminded.ums.exeptions;

public class ServerUnavailableException extends RuntimeException {
    public ServerUnavailableException(String message) {
        super(message);
    }

    public ServerUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }

}
