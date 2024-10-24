package com.example.mailtracer.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class OfficeRequest {

    @NotBlank(message = "Индекс не может быть пустым")
    @Pattern(regexp = "\\d{6}", message = "Индекс должен быть в формате 123456")
    private String index;

    @NotBlank(message = "Название офиса не может быть пустым")
    private String name;

    @NotBlank(message = "Адрес офиса не может быть пустым")
    private String address;
}
