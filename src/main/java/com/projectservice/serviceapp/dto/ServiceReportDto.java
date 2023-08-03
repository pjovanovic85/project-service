package com.projectservice.serviceapp.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.projectservice.serviceapp.model.ServiceReportSparePart;
import com.projectservice.serviceapp.model.SparePart;
import lombok.*;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;


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
    private DeviceDto device;
    private ClientDto client;
    private List<SparePartDto> sparePartList;
}
