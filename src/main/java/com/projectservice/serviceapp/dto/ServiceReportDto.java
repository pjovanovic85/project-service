package com.projectservice.serviceapp.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.projectservice.serviceapp.model.SparePart;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceReportDto {

    private Integer id;
    private String accessories;
    private String additionRemark;
    private String failureDescription;
    private String serviceDescription;
    private String labor;
    private String technician;
    private ServiceReportStatusDto status;
    private Date receiptDate;
    private Date deviceCheckOut;
    @JsonBackReference
    private DeviceDto device;
    private ClientDto client;
    private List<SparePartDto> sparePartList;
}
