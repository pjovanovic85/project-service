package com.projectservice.serviceapp.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceReportStatusDto {

    private Integer id;
    private int statusCode;
    private String description;
    private Date modifyDate;
    @JsonBackReference
    private ServiceReportDto serviceReport;

}
