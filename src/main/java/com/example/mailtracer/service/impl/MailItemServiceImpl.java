package com.example.mailtracer.service.impl;

import com.example.mailtracer.enums.MailStatus;
import com.example.mailtracer.requests.MailItemRequest;
import com.example.mailtracer.responses.MailItemResponse;
import com.example.mailtracer.service.MailItemService;
import com.example.mailtracer.util.ValidationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailItemServiceImpl implements MailItemService {

    @Autowired
    private ValidationUtils validationUtils;

    @Override
    public ResponseEntity<MailItemResponse> registration(MailItemRequest mailItemRequest) {

        validationUtils.validationRequest(mailItemRequest);

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
