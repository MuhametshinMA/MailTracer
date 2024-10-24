package com.example.mailtracer.service.impl;

import com.example.mailtracer.entity.Mail;
import com.example.mailtracer.entity.Office;
import com.example.mailtracer.enums.ErrorCode;
import com.example.mailtracer.enums.Status;
import com.example.mailtracer.exceptions.BusinessException;
import com.example.mailtracer.exceptions.MailEntityNotFoundException;
import com.example.mailtracer.exceptions.handler.ExceptionHandler;
import com.example.mailtracer.repository.MailRepository;
import com.example.mailtracer.repository.OfficeRepository;
import com.example.mailtracer.requests.MailRequest;
import com.example.mailtracer.responses.MailResponse;
import com.example.mailtracer.responses.OfficeResponse;
import com.example.mailtracer.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final MailRepository mailRepository;

    private final OfficeRepository officeRepository;

    @Override
    @Transactional
    public ResponseEntity<MailResponse> registration(MailRequest mailRequest) {

        Mail mail = Mail.builder()
                .recipient(mailRequest.getRecipient())
                .index(mailRequest.getIndex())
                .type(mailRequest.getType())
                .address(mailRequest.getAddress())
                .status(Status.REGISTERED.getCode())
                .build();

        Office office = officeRepository
                .findById(mailRequest.getOfficeId())
                .orElseThrow(() -> new MailEntityNotFoundException(
                        Office.class, mailRequest.getOfficeId()));

        List<Office> officeList = Collections.singletonList(office);
        mail.setOffices(officeList);

        Mail savedMail = mailRepository.save(mail);
        savedMail.setStatus(Status.REGISTERED.getCode());

        return ResponseEntity.ok(buildResponse(savedMail, office));
    }

    @Override
    public ResponseEntity<MailResponse> intermediateOfficeRegistration(Long mailId, Long officeId) {

        Mail mail = getEntityOrThrow(mailId, mailRepository, Mail.class);
        Office office = getEntityOrThrow(officeId, officeRepository, Office.class);

        List<Office> officeList = mail.getOffices();

        BusinessException b = ExceptionHandler.raiseException(BusinessException.class,
                ErrorCode.MAIL_UNREGISTERED_ERROR);
        if (officeList.isEmpty()) throw b;

        mail.getOffices().add(office);

        mail.setStatus(mail.getIndex().equals(office.getIndex())
                ? Status.ARRIVED.getCode()
                : Status.IN_TRANSIT.getCode());

        Mail updatedMail = mailRepository.save(mail);

        return ResponseEntity.ok(buildResponse(updatedMail, office));
    }

    @Override
    public ResponseEntity<MailResponse> officeSend(Long mailId, Long officeId) {

        Mail mail = getEntityOrThrow(mailId, mailRepository, Mail.class);
        Office office = getEntityOrThrow(officeId, officeRepository, Office.class);

        if (!((mail.getStatus().equals(Status.IN_TRANSIT.getCode())
                || mail.getStatus().equals(Status.REGISTERED.getCode()))
                && mail.getOffices().contains(office))) {
            throw ExceptionHandler.raiseException(BusinessException.class,
                    ErrorCode.MAIL_SEND_UNREGISTERD_IN_TRANSIT_OFFICE_ERROR);
        }

        if (mail.getStatus().equals(Status.ARRIVED.getCode())
                || mail.getStatus().equals(Status.DELIVERED.getCode())) {
            throw ExceptionHandler.raiseException(BusinessException.class, ErrorCode.MAIL_SEND_ERROR);
        }

        mail.setStatus(Status.SENT.getCode());

        Mail updatedMail = mailRepository.save(mail);

        return ResponseEntity.ok(buildResponse(updatedMail, office));
    }

    @Override
    public ResponseEntity<MailResponse> delivery(Long mailId, Long officeId) {

        Mail mail = getEntityOrThrow(mailId, mailRepository, Mail.class);
        Office office = getEntityOrThrow(officeId, officeRepository, Office.class);

        if (!mail.getStatus().equals(Status.ARRIVED.getCode()))
            ExceptionHandler.raiseException(BusinessException.class, ErrorCode.MAIL_DELIVERY_ERROR);

        mail.setStatus(Status.DELIVERED.getCode());

        Mail updatedMail = mailRepository.save(mail);

        return ResponseEntity.ok(buildResponse(updatedMail, office));
    }

    private <T, ID> T getEntityOrThrow(ID id, JpaRepository<T, ID> repository, Class<T> entityClass) {
        return repository.findById(id)
                .orElseThrow(() -> new MailEntityNotFoundException(entityClass, id));
    }

    private MailResponse buildResponse(Mail mail, Office office) {
        return MailResponse.builder()
                .id(mail.getId())
                .recipient(mail.getRecipient())
                .type(mail.getType())
                .address(mail.getAddress())
                .index(mail.getIndex())
                .status(mail.getStatus())
                .officeResponse(OfficeResponse.builder()
                        .id(office.getId())
                        .name(office.getName())
                        .index(mail.getIndex())
                        .address(office.getAddress())
                        .build())
                .build();
    }
}
