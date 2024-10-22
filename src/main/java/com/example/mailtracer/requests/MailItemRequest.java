package com.example.mailtracer.requests;

import com.example.mailtracer.annotations.ValueOfEnum;
import com.example.mailtracer.enums.MailType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class MailItemRequest {
    @NotBlank(message = "Получатель не может быть пустым")
    private String recipient;

    @NotBlank(message = "Тип отправления не может быть пустым")
    @ValueOfEnum(enumClass = MailType.class
            , message = "Тип отправления должен быть: Письмо, Посылка, Бандероль, Открытка")
    private String type;

    @NotBlank(message = "Индекс не может быть пустым")
    @Pattern(regexp = "\\d{6}", message = "Индекс должен быть в формате 123456")
    private String index;

    @NotBlank(message = "Адрес не может быть пустым")
    private String address;
}
