package com.example.mailtracer.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OfficeResponse {

    private Long id;
    private String name;
    private String address;
    private String index;
}
