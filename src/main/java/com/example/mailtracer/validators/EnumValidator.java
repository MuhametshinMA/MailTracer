package com.example.mailtracer.validators;

import com.example.mailtracer.annotations.ValueOfEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumValidator implements ConstraintValidator<ValueOfEnum, String> {
    private Enum<?>[] enumValues; // Массив значений перечисления

    @Override
    public void initialize(ValueOfEnum annotation) {
        enumValues = annotation.enumClass().getEnumConstants();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        for (Enum<?> enumValue : enumValues) {
            if (enumValue.name().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
