package com.projectservice.serviceapp.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class SparePart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "serial_number")
    private String serialNo;
    private String name;
    private String description;
    @ManyToMany
    private List<ServiceReport> serviceReportList;
}
