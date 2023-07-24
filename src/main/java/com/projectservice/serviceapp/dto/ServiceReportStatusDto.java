package com.projectservice.serviceapp.dto;

import lombok.*;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ServiceReportStatusDto {

    private Integer id;
    private int statusCode;
    private String description;
    private Date modifyDate;

}
