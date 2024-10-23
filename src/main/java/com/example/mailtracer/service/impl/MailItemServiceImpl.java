package com.example.mailtracer.service.impl;

import com.example.mailtracer.entity.MailItem;
import com.example.mailtracer.entity.MailOffice;
import com.example.mailtracer.enums.MailStatus;
import com.example.mailtracer.exceptions.MailOfficeNotFoundException;
import com.example.mailtracer.repository.MailItemRepository;
import com.example.mailtracer.repository.MailOfficeRepository;
import com.example.mailtracer.requests.MailItemRequest;
import com.example.mailtracer.responses.MailItemResponse;
import com.example.mailtracer.service.MailItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MailItemServiceImpl implements MailItemService {

    private final MailItemRepository mailItemRepository;

    private final MailOfficeRepository mailOfficeRepository;

    @Override
    @Transactional
    public ResponseEntity<MailItemResponse> registration(MailItemRequest mailItemRequest) {

        MailItem mailItem = MailItem.builder()
                .recipient(mailItemRequest.getRecipient())
                .index(mailItemRequest.getIndex())
                .type(mailItemRequest.getType())
                .address(mailItemRequest.getAddress())
                .status(MailStatus.REGISTERED.getCode())
                .build();

        MailOffice mailOffice = mailOfficeRepository
                .findById(mailItemRequest.getOfficeId())
                .orElseThrow(() -> new MailOfficeNotFoundException(mailItemRequest.getOfficeId()));

        mailItem.getMailOffices().add(mailOffice);

        MailItem savedMailItem = mailItemRepository.save(mailItem);

        MailItemResponse mailItemResponse = MailItemResponse.builder()
                .id(savedMailItem.getId())
                .recipient(mailItemRequest.getRecipient())
                .type(mailItemRequest.getType())
                .address(mailItemRequest.getAddress())
                .index(mailItemRequest.getIndex())
                .status(MailStatus.REGISTERED.getCode())
                .build();
        return ResponseEntity.ok(mailItemResponse);
    }
}
