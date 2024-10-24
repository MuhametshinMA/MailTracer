package com.example.mailtracer.exceptions.handler;

import com.example.mailtracer.enums.ErrorCode;
import com.example.mailtracer.exceptions.BusinessException;

public class ExceptionHandler {
    public static <T extends Exception> T raiseException(Class<T> exceptionClass, ErrorCode error) throws T {
        try {
            return exceptionClass.getConstructor(ErrorCode.class).newInstance(error);
        } catch (Exception e) {
            throw new RuntimeException("Не удалось создать исключение", e);
        }
    }
}
