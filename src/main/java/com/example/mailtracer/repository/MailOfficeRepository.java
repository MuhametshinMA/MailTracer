package com.example.mailtracer.repository;

import com.example.mailtracer.entity.MailOffice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailOfficeRepository extends JpaRepository<MailOffice, Long> {
}
