package com.example.mailtracer.exceptions;

import com.example.mailtracer.enums.ErrorCode;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BusinessException extends RuntimeException implements MailErrors {

    private final ErrorCode errorCode;

    @Override
    public ErrorCode getErrorCode() {
        return errorCode;
    }

    @Override
    public String toString() {
        return "BusinessException " + errorCode.getCode() + ": " + getMessage();
    }
}
