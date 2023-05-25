package com.mediscreen.patientservice.exceptions;

public class MandatoryFieldsException extends RuntimeException {
    public MandatoryFieldsException(String s) {
        super(s);
    }
}
