package com.example.mailtracer.responses;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MailResponse {
    private Long id;
    private String recipient;
    private String type;
    private String index;
    private String address;
    private String status;
    private List<OfficeResponse> officeResponse;
}
