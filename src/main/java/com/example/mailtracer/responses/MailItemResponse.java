package com.example.mailtracer.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MailItemResponse {
    private Long id;
    private String recipient;
    private String type;
    private String index;
    private String address;
    private String status;
}
