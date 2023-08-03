package com.projectservice.serviceapp.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
