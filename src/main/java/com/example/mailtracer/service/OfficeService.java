package com.example.mailtracer.service;

import com.example.mailtracer.requests.OfficeRequest;
import com.example.mailtracer.responses.OfficeResponse;
import org.springframework.http.ResponseEntity;

public interface OfficeService {

    ResponseEntity<OfficeResponse> registration(OfficeRequest request);
}
