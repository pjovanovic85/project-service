package com.projectservice.serviceapp.dto;

import lombok.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ServiceReportDto {

    private Integer id;
    private String accessories;
    private String additionRemark;
    private String failureDescription;
    private String serviceDescription;
    private String labor;
    private String technician;
    private List<ServiceReportStatusDto> statusList;
    private Integer currentStatusCode;
    private String receiptDate;
    private String checkOutDate;
    private DeviceDto device;
    private ClientDto client;
    private List<SparePartDto> sparePartList;
}
