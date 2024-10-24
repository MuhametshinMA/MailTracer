package com.example.mailtracer.service.impl;

import com.example.mailtracer.entity.MailItem;
import com.example.mailtracer.entity.MailOffice;
import com.example.mailtracer.enums.MailStatus;
import com.example.mailtracer.exceptions.MailEntityNotFoundException;
import com.example.mailtracer.exceptions.UnregisteredMailException;
import com.example.mailtracer.repository.MailItemRepository;
import com.example.mailtracer.repository.MailOfficeRepository;
import com.example.mailtracer.requests.MailItemRequest;
import com.example.mailtracer.responses.MailItemResponse;
import com.example.mailtracer.responses.MailOfficeResponse;
import com.example.mailtracer.service.MailItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

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
                .orElseThrow(() -> new MailEntityNotFoundException(
                        MailOffice.class, mailItemRequest.getOfficeId()));

        List<MailOffice> mailOfficeList = Collections.singletonList(mailOffice);
        mailItem.setMailOffices(mailOfficeList);

        MailItem savedMailItem = mailItemRepository.save(mailItem);

        MailItemResponse mailItemResponse = MailItemResponse.builder()
                .id(savedMailItem.getId())
                .recipient(mailItemRequest.getRecipient())
                .type(mailItemRequest.getType())
                .address(mailItemRequest.getAddress())
                .index(mailItemRequest.getIndex())
                .status(MailStatus.REGISTERED.getCode())
                .mailOfficeResponse(MailOfficeResponse.builder()
                        .id(mailOffice.getId())
                        .name(mailOffice.getName())
                        .index(mailItem.getIndex())
                        .address(mailOffice.getAddress())
                        .build())
                .build();
        return ResponseEntity.ok(mailItemResponse);
    }

    @Override
    public ResponseEntity<MailItemResponse> intermediateOfficeRegistration(Long mailId, Long officeId) {

        MailItem mailItem = getEntity(mailId, mailItemRepository, MailItem.class);
        MailOffice mailOffice = getEntity(officeId, mailOfficeRepository, MailOffice.class);

        List<MailOffice> mailOfficeList = mailItem.getMailOffices();
        if (mailOfficeList.isEmpty()) throw new UnregisteredMailException(mailId, officeId);

        mailItem.getMailOffices().add(mailOffice);
        mailItem.setStatus(MailStatus.IN_TRANSIT.getCode());

        MailItem updtedMailItem = mailItemRepository.save(mailItem);

        return ResponseEntity.ok(MailItemResponse.builder()
                .id(updtedMailItem.getId())
                .recipient(updtedMailItem.getRecipient())
                .type(updtedMailItem.getType())
                .address(updtedMailItem.getAddress())
                .index(updtedMailItem.getIndex())
                .status(updtedMailItem.getStatus())
                .mailOfficeResponse(MailOfficeResponse.builder()
                        .id(mailOffice.getId())
                        .name(mailOffice.getName())
                        .index(updtedMailItem.getIndex())
                        .address(mailOffice.getAddress())
                        .build())
                .build());
    }

    @Override
    public ResponseEntity<MailItemResponse> intermediateOfficeSend(Long mailId, Long officeId) {

        MailItem mailItem = getEntity(mailId, mailItemRepository, MailItem.class);
        MailOffice mailOffice = getEntity(officeId, mailOfficeRepository, MailOffice.class);

        //проверить письмо на статус, если нет статуса transit с таким же id офиса то исключение

        return null;
    }

    private <T, ID> T getEntity(ID id, JpaRepository<T, ID> repository, Class<T> entityClass) {
        return repository.findById(id)
                .orElseThrow(() -> new MailEntityNotFoundException(entityClass, id));
    }
}
