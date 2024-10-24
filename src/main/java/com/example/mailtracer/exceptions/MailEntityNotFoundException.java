package com.example.mailtracer.exceptions;

public class MailEntityNotFoundException extends RuntimeException {
    public MailEntityNotFoundException(Class<?> entityClass, Object id) {

        super(entityClass.getSimpleName() + " not found with id " + id);
    }
}
