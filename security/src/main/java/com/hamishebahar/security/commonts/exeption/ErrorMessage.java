package com.hamishebahar.security.commonts.exeption;

import java.util.Date;

public class ErrorMessage {
    private int statusCode;
    private int errorCode;
    private Date timestamp;
    private String message;
    private String description;

    public ErrorMessage(int statusCode, int errorCode, Date timestamp, String message, String description) {
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.timestamp = timestamp;
        this.message = message;
        this.description = description;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}