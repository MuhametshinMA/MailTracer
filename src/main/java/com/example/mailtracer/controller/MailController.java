package com.example.mailtracer.controller;

import com.example.mailtracer.enums.MailStatus;
import com.example.mailtracer.requests.MailItemRequest;
import com.example.mailtracer.responses.MailItemResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/mail")
public class MailController {

    @PostMapping("/registration")
    public ResponseEntity<?> save(@RequestBody MailItemRequest mailItemRequest) {

        MailItemResponse mailItemResponse = MailItemResponse.builder()
                .recipient(mailItemRequest.getRecipient())
                .type(mailItemRequest.getType())
                .address(mailItemRequest.getAddress())
                .index(mailItemRequest.getIndex())
                .status(MailStatus.REGISTERED.getCode())
                .build();
        return ResponseEntity.ok(mailItemResponse);
    }
}
