package com.example.mailtracer.repository;

import com.example.mailtracer.entity.MailOffice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MailOfficeRepository extends JpaRepository<MailOffice, Long> {
    Optional<MailOffice> findByNameAndIndex(String name, String index);
}
