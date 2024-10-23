package com.example.mailtracer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "mail_office")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MailOffice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String index;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @ManyToMany(mappedBy = "mailOffices")
    private Set<MailItem> mailItems = new HashSet<>();
}
