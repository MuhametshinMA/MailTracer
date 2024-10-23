package com.example.mailtracer.controller;

import com.example.mailtracer.requests.MailOfficeRequest;
import com.example.mailtracer.service.MailOfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/office")
@RequiredArgsConstructor
public class MailOfficeController {

    private final MailOfficeService mailOfficeService;

    @PostMapping("/registration")
    public ResponseEntity<?> save(@Valid @RequestBody MailOfficeRequest request) {

        return mailOfficeService.registration(request);
    }

}
