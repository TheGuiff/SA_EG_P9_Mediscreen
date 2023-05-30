package com.mediscreen.patientservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoPatientException extends RuntimeException {
    public NoPatientException(String s) {
        super(s);
    }

}
