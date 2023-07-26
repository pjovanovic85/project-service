package com.projectservice.serviceapp.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

    @OneToMany(mappedBy = "client")
    private List<ServiceReport> serviceReports;

}
