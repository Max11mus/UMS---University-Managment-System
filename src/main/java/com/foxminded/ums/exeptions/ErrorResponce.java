package com.foxminded.ums.exeptions;

import org.springframework.http.HttpStatus;

public class ErrorResponce {
    private HttpStatus httpStatusCode;
    private String message;
    private String description;
    public ErrorResponce(HttpStatus httpStatusCode, String message, String description) {
        this.httpStatusCode = httpStatusCode;
        this.message = message;
        this.description = description;
    }

    public HttpStatus getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(HttpStatus httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
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
