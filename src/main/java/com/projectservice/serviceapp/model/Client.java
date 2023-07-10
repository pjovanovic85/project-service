package com.projectservice.serviceapp.model;

import lombok.*;

import javax.persistence.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    private String lastName;
    @Column(name = "phone_number", nullable = false)
    private String phoneNo;
    @Column(nullable = false)
    private String email;
    private String address;

}
