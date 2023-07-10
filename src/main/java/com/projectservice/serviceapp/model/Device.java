package com.projectservice.serviceapp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.sql.Blob;
import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    private Warranty warranty;
}
