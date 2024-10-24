package com.example.mailtracer.service;

import com.example.mailtracer.requests.MailRequest;
import com.example.mailtracer.responses.MailResponse;
import org.springframework.http.ResponseEntity;

public interface MailService {

    ResponseEntity<MailResponse> registration(MailRequest mailRequest);

    ResponseEntity<MailResponse> intermediateOfficeRegistration(Long mailId, Long officeId);

    ResponseEntity<MailResponse> officeSend(Long mailItem, Long officeId);

    ResponseEntity<MailResponse> delivery(Long mailItem, Long officeId);
}
