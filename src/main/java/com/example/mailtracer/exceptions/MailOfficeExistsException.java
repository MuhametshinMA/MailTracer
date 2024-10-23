package com.example.mailtracer.exceptions;

public class MailOfficeExistsException extends RuntimeException{
    public MailOfficeExistsException(String name, String index) {
        super("Office with name: " + name + " and index: " + index + " exists");
    }
}
