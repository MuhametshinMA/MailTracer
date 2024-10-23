package com.example.mailtracer.responses;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class MailOfficeResponse {

    private Long id;
    private String name;
    private String address;
    private String index;
}
