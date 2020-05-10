package com.bridgelabz.exception;

public class CliniqueManagementException extends Throwable {
    public Exceptions type;

    public CliniqueManagementException(Exceptions type) {
        this.type = type;
    }

    public enum Exceptions{
        APPOINTMENTS_FULL,
        DATE_FORMAT_INCORRECT,
        DOCTOR_UNAVAILABLE
    }
}
