package com.projectservice.serviceapp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.sql.Blob;
import java.sql.Date;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "device_group", nullable = false)
    private String deviceGroup;
    @Column(nullable = false)
    private String manufacturer;
    @Column(nullable = false)
    private String model;
    @Column(name = "serial_number", nullable = false)
    private String serialNumber;
    @Column(name = "additional_param")
    private String additionalParam;
    @Column(name = "purchase_date")
    private Date purchaseDate;
    @Column(name = "warranty_period")
    private int warrantyPeriod;
    @Lob
    private Blob picture;

    @OneToMany(mappedBy = "device")
    private List<ServiceReport> serviceReports;
}
