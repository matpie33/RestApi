package com.java.restapi.errorHandling;

public class NoDataReturnedException extends RuntimeException {

    public NoDataReturnedException(String message) {
        super(message);
    }
}
