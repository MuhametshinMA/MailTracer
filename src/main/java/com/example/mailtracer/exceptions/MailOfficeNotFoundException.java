package com.example.mailtracer.exceptions;

public class MailOfficeNotFoundException extends RuntimeException{
    public MailOfficeNotFoundException(Long id) {
        super("Office not found with Id: " + id);
    }
}
