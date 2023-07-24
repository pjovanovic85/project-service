package com.projectservice.serviceapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class ServiceReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String accessories;
    private String additionRemark;
    private String failureDescription;
    private String serviceDescription;
    private String labor;
    private String technician;
    @OneToOne(mappedBy = "serviceReport", cascade = CascadeType.ALL)
    private ServiceReportStatus status;
    private Date receiptDate;
    private Date checkOutDate;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<SparePart> sparePartList;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Device device;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Client client;


}
