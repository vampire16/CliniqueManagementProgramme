package com.bridgelabz.exception;

public class Exception extends Throwable {
    public Exceptions type;

    public Exception(Exceptions type) {
        this.type = type;
    }

    public enum Exceptions{
        APPOINTMENTS_FULL,
        DATE_FORMAT_INCORRECT,
        DOCTOR_UNAVAILABLE
    }
}
