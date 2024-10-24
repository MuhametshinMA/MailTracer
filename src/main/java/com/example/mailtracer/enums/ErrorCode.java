package com.example.mailtracer.enums;

public enum ErrorCode {

    MAIL_SEND_ERROR(1, "Ошибка отправки письма"),
    MAIL_DELIVERY_ERROR(2, "Ошибка выдачи письма"),
    MAIL_SEND_UNREGISTERD_IN_TRANSIT_OFFICE_ERROR(3,
            "ошибка отправки письма не зарегистрированной в промежуточном офисе"),
    MAIL_UNREGISTERED_ERROR(4, "Не зарегистрированное письмо"),
    OFFICE_EXISTS_ERROR(5, "Офис не найден");

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
