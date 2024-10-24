package com.example.mailtracer.exceptions;

public class UnregisteredMailException extends RuntimeException {
    public UnregisteredMailException(Long mailId, Long officeId) {
        super("Mail with id: " + mailId + " was not registered, found in office with id: " + officeId);
    }
}
