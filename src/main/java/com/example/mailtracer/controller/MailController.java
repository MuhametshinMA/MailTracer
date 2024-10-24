package com.example.mailtracer.controller;

import com.example.mailtracer.requests.MailRequest;
import com.example.mailtracer.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    @PostMapping("/registration")
    public ResponseEntity<?> save(@Valid @RequestBody MailRequest mailRequest) {

        return mailService.registration(mailRequest);
    }

    @PutMapping("/intermediateRegistration/{mailId}/{officeId}")
    public ResponseEntity<?> intermediateOfficeRegistration(@PathVariable long mailId, @PathVariable long officeId) {

        return mailService.intermediateOfficeRegistration(mailId, officeId);
    }

    @PutMapping("/send/{mailId}/{officeId}")
    public ResponseEntity<?> officeSend(@PathVariable long mailId, @PathVariable long officeId) {

        return mailService.officeSend(mailId, officeId);
    }

    @PutMapping("/arrived/{mailId}/{officeId}")
    public ResponseEntity<?> delivery(@PathVariable long mailId, @PathVariable long officeId) {

        return mailService.delivery(mailId, officeId);
    }
}
