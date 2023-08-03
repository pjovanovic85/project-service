package com.projectservice.serviceapp.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class SparePart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "serial_number")
    private String serialNo;
    private String name;
    private String description;
    private int availableQuantity;
    private Double price;
    private String location;
    @OneToMany(mappedBy = "serviceReportSparePartId.sparePart", cascade = CascadeType.ALL)
    private List<ServiceReportSparePart> serviceReportSpareParts;
}
