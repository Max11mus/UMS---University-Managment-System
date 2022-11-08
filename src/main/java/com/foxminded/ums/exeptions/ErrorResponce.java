package com.foxminded.ums.exeptions;

public class ErrorResponce {
    private int statuscode;
    private String message;
    private String description;

    public ErrorResponce() {
    }

    public ErrorResponce(int statuscode, String message, String description) {
        this.statuscode = statuscode;
        this.message = message;
        this.description = description;
    }

    public int getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(int statuscode) {
        this.statuscode = statuscode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
