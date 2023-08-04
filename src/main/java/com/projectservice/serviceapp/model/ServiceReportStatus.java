package com.projectservice.serviceapp.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class ServiceReportStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int statusCode;
    private String description;
    private LocalDateTime modifyDate;
    @ManyToOne()
    private ServiceReport serviceReport;
}
