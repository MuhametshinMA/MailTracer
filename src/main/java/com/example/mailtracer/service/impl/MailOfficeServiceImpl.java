package com.example.mailtracer.service.impl;

import com.example.mailtracer.entity.MailOffice;
import com.example.mailtracer.exceptions.MailOfficeExistsException;
import com.example.mailtracer.repository.MailOfficeRepository;
import com.example.mailtracer.requests.MailOfficeRequest;
import com.example.mailtracer.responses.MailOfficeResponse;
import com.example.mailtracer.service.MailOfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MailOfficeServiceImpl implements MailOfficeService {

    private final MailOfficeRepository mailOfficeRepository;
    @Override
    @Transactional
    public ResponseEntity<MailOfficeResponse> registration(MailOfficeRequest request) {

        if (mailOfficeRepository.findByNameAndIndex(
                request.getName(),
                request.getIndex())
                .isPresent()
        ) {
            throw new MailOfficeExistsException(request.getName(), request.getIndex());
        }

        MailOffice mailOffice = MailOffice.builder()
                .index(request.getIndex())
                .name(request.getName())
                .address(request.getAddress())
                .build();

        MailOffice savedMailOffice = mailOfficeRepository.save(mailOffice);

        return ResponseEntity.ok(MailOfficeResponse.builder()
                .id(savedMailOffice.getId())
                .name(savedMailOffice.getName())
                .address(savedMailOffice.getAddress())
                .index(savedMailOffice.getIndex())
                .build());
    }
}
