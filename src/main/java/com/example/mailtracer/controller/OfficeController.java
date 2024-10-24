package com.example.mailtracer.controller;

import com.example.mailtracer.requests.OfficeRequest;
import com.example.mailtracer.service.OfficeService;
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
public class OfficeController {

    private final OfficeService officeService;

    @PostMapping("/registration")
    public ResponseEntity<?> save(@Valid @RequestBody OfficeRequest request) {

        return officeService.registration(request);
    }

}
