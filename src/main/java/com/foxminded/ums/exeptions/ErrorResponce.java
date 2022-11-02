package com.foxminded.ums.exeptions;

public class ErrorResponce {
    private String message;

    public ErrorResponce() {
    }

    public ErrorResponce(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
