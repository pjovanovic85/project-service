package com.projectservice.serviceapp.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.sql.Date;
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
    @JsonManagedReference
    private List<ServiceReportStatusDto> statusList;
    private Integer currentStatusCode;
    private Date receiptDate;
    private Date checkOutDate;
    @JsonManagedReference
    private DeviceDto device;
    @JsonManagedReference
    private ClientDto client;
    private List<SparePartDto> sparePartList;
}
