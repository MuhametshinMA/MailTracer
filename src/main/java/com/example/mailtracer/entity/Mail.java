package com.example.mailtracer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "mail_item")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_recipient", nullable = false)
    private String recipient;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String index;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String status;

    @ManyToMany
    @JoinTable(
            name = "mail_item_mail_office",
            joinColumns = @JoinColumn(name = "mail_item_id"),
            inverseJoinColumns = @JoinColumn(name = "mail_office_id")
    )
    private List<Office> offices;
}
