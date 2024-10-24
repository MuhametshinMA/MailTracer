package com.example.mailtracer.enums;

public enum Type {
    LETTER("Письмо"),
    PACKAGE("Посылка"),
    PARCEL("Бандероль"),
    POSTCARD("Открытка");

    private final String description;

    Type(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
