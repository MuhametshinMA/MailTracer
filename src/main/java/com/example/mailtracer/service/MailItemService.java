package com.example.mailtracer.service;

import com.example.mailtracer.requests.MailItemRequest;
import com.example.mailtracer.responses.MailItemResponse;
import org.springframework.http.ResponseEntity;

public interface MailItemService {

    ResponseEntity<MailItemResponse> registration(MailItemRequest mailItemRequest);

    ResponseEntity<MailItemResponse> intermediateOfficeRegistration(Long mailId, Long officeId);

    ResponseEntity<MailItemResponse> intermediateOfficeSend(Long mailItem, Long officeId);
}
