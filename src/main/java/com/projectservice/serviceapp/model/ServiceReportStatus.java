package com.projectservice.serviceapp.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class ServiceReportStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int statusCode;
    private String description;
    private Date modifyDate;
    @OneToOne()
    private ServiceReport serviceReport;
}
