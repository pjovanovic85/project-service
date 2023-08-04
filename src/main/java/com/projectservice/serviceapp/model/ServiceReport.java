package com.projectservice.serviceapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
    private Integer currentStatusCode;
    @OneToMany(mappedBy = "serviceReport", cascade = CascadeType.ALL)
    private List<ServiceReportStatus> statusList;
    private LocalDateTime receiptDate;
    private LocalDateTime checkOutDate;
    @OneToMany(mappedBy = "serviceReportSparePartId.serviceReport", cascade = CascadeType.ALL)
    private List<ServiceReportSparePart> serviceReportSpareParts;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Device device;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Client client;
}
