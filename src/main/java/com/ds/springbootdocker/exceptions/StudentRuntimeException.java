package com.ds.springbootdocker.exceptions;

public class StudentRuntimeException extends RuntimeException {

    public StudentRuntimeException() {
        super();
    }

    public StudentRuntimeException(String message) {
        super(message);
    }
}
