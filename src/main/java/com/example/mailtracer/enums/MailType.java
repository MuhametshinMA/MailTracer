package com.example.mailtracer.enums;

public enum MailType {
    LETTER("Письмо"),
    PACKAGE("Посылка"),
    PARCEL("Бандероль"),
    POSTCARD("Открытка");

    private final String description;

    MailType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
