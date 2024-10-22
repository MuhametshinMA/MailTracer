package com.example.mailtracer.service;

import com.example.mailtracer.requests.MailItemRequest;
import com.example.mailtracer.responses.MailItemResponse;
import org.springframework.http.ResponseEntity;

public interface MailItemService {
    ResponseEntity<MailItemResponse> registration(MailItemRequest mailItemRequest);
}
