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
    private String name;
    private String lastName;
    @Column(name = "phone_number")
    private String phoneNo;
    private String email;

}
