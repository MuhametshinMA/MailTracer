package com.example.mailtracer.controller;

import com.example.mailtracer.requests.MailItemRequest;
import com.example.mailtracer.service.MailItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/mail")
public class MailController {

    @Autowired
    private MailItemService mailItemService;

    @PostMapping("/registration")
    public ResponseEntity<?> save(@Valid @RequestBody MailItemRequest mailItemRequest) {

        return mailItemService.registration(mailItemRequest);
    }
}
