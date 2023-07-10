package com.projectservice.serviceapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Blob;
import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Warranty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "purchase_date")
    private Date purchaseDate;
    @Column(name = "warranty_period")
    private int warrantyPeriod;
    @Lob
    private Blob picture;
    @OneToOne(mappedBy = "warranty")
    @JsonBackReference
    private Device device;
}
