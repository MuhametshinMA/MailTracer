package com.example.mailtracer.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "mail_office")
@Data
public class MailOffice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String index;

    @Column(nullable = false)
    private String address;

    @ManyToMany(mappedBy = "mailOffices")
    private Set<MailItem> mailItems = new HashSet<>();
}
