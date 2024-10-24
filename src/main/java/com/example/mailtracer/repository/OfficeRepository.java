package com.example.mailtracer.repository;

import com.example.mailtracer.entity.Office;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OfficeRepository extends JpaRepository<Office, Long> {
    Optional<Office> findByNameAndIndex(String name, String index);
}
