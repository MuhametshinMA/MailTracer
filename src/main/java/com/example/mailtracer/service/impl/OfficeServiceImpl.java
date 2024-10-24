package com.example.mailtracer.service.impl;

import com.example.mailtracer.entity.Office;
import com.example.mailtracer.enums.ErrorCode;
import com.example.mailtracer.exceptions.BusinessException;
import com.example.mailtracer.exceptions.handler.ExceptionHandler;
import com.example.mailtracer.repository.OfficeRepository;
import com.example.mailtracer.requests.OfficeRequest;
import com.example.mailtracer.responses.OfficeResponse;
import com.example.mailtracer.service.OfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OfficeServiceImpl implements OfficeService {

    private final OfficeRepository officeRepository;

    @Override
    @Transactional
    public ResponseEntity<OfficeResponse> registration(OfficeRequest request) {

        if (officeRepository.findByNameAndIndex(
                        request.getName(),
                        request.getIndex())
                .isPresent()
        ) {
            ExceptionHandler.raiseException(BusinessException.class, ErrorCode.OFFICE_EXISTS_ERROR);
        }

        Office office = Office.builder()
                .index(request.getIndex())
                .name(request.getName())
                .address(request.getAddress())
                .build();

        Office savedOffice = officeRepository.save(office);

        return ResponseEntity.ok(OfficeResponse.builder()
                .id(savedOffice.getId())
                .name(savedOffice.getName())
                .address(savedOffice.getAddress())
                .index(savedOffice.getIndex())
                .build());
    }
}
