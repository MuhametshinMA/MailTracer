package com.example.mailtracer.requests;

import com.example.mailtracer.enums.MailType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class MailItemRequest {
    @NotBlank(message = "Получатель не может быть пустым")
    private String recipient;

    @NotBlank(message = "Тип отправления не может быть пустым")
//    @Enum(enumClass = MailType.class
//            , message = "Тип отправления должен быть: Письмо, Посылка, Бандероль, Открытка")
    private String type;
    private String index;
    private String address;
}
