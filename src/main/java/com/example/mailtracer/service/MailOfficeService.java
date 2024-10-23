package com.example.mailtracer.service;

import com.example.mailtracer.requests.MailOfficeRequest;
import com.example.mailtracer.responses.MailOfficeResponse;
import org.springframework.http.ResponseEntity;

public interface MailOfficeService {

    ResponseEntity<MailOfficeResponse> registration(MailOfficeRequest request);
}
