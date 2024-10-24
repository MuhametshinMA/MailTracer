package com.example.mailtracer.enums;

public enum Status {
    REGISTERED("REG", "Зарегистрировано"),
    SENT("SENT", "Отправлено"),
    IN_TRANSIT("TRANSIT", "Зарегистрировано в промежуточном пункте"),
    SENT_FROM_TRANSIT("FROM_TRANSIT", "Отправлено из промежуточного пункта"),
    ARRIVED("ARRIVED", "Прибыло в назначенный пункт"),
    DELIVERED("DELIVERED", "Выдано");

    private final String code;
    private final String description;

    Status(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

}
