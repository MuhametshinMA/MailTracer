package com.example.mailtracer.validators;

import com.example.mailtracer.annotations.ValueOfEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumValidator implements ConstraintValidator<ValueOfEnum, String> {
    private List<String> acceptedValues; // Массив значений перечисления

    @Override
    public void initialize(ValueOfEnum annotation) {
        acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Возвращаем true, если значение null (можно обработать отдельно)
        }
        return acceptedValues.contains(value);
    }
}
