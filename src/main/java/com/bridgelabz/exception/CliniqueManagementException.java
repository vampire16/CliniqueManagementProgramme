package com.bridgelabz.exception;

public class CliniqueManagementException extends Exception {
    //    ENUM VARIABLE
    public Exceptions type;

    //    PARAMETERIZE CONSTRUCTOR
    public CliniqueManagementException(Exceptions type) {
        this.type = type;
    }

    public enum Exceptions {
        APPOINTMENTS_FULL,
        DATE_FORMAT_INCORRECT,
        DOCTOR_UNAVAILABLE
    }
}
