package com.example.mailtracer.util;

import com.example.mailtracer.exceptions.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ValidationUtils {

    private final javax.validation.Validator validator;

    public <T> void validationRequest(T request) {
        if (request != null) {
            Set<ConstraintViolation<T>> violations = validator.validate(request);
            if (!violations.isEmpty()) {
                List<String> errors = violations.stream()
                        .map(ConstraintViolation::getMessage)
                        .collect(Collectors.toList());
                throw new ValidationException(errors);
            }
        }
    }
}
