package com.renison.exception;

public class BaseException extends RuntimeException {
    private Long debugNumber;
    private String debugMessage; //should include details of the exception
    private Object data;

    public BaseException(Long debugNumber, String errorMessage, String debugMessage) {
        super(errorMessage);
        this.debugMessage = debugMessage;
        this.debugNumber = debugNumber;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Long getDebugNumber() {
        return debugNumber;
    }

    public void setDebugNumber(Long debugNumber) {
        this.debugNumber = debugNumber;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }
}
