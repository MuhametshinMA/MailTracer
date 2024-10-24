package com.example.mailtracer.requests;

import com.example.mailtracer.annotations.ValueOfEnum;
import com.example.mailtracer.enums.Type;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class MailRequest {

    @NotBlank(message = "Получатель не может быть пустым")
    private String recipient;

    @NotBlank(message = "Тип отправления не может быть пустым")
    @ValueOfEnum(enumClass = Type.class
            , message = "Тип отправления должен быть: Письмо, Посылка, Бандероль, Открытка")
    private String type;

    @NotBlank(message = "Индекс не может быть пустым")
    @Pattern(regexp = "\\d{6}", message = "Индекс должен быть в формате 123456")
    private String index;

    @NotBlank(message = "Адрес не может быть пустым")
    private String address;

    @NotNull(message = "Идентификатор почтового отделения не должен быть пустым")
    private Long officeId;
}
