package com.mediscreen.patientservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MandatoryFieldsException extends RuntimeException {
    public MandatoryFieldsException(String s) {
        super(s);
    }
}
