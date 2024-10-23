package com.example.mailtracer.repository;

import com.example.mailtracer.entity.MailItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailItemRepository extends JpaRepository<MailItem, Long> {
}
