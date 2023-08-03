package com.projectservice.serviceapp.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import java.sql.Blob;
import java.sql.Date;
import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DeviceDto {

    private Integer id;
    private String deviceGroup;
    private String manufacturer;
    private String model;
    private String serialNumber;
    private String additionalParam;
    private Date purchaseDate;
    private int warrantyPeriod;
    private Blob picture;
    boolean haveHistory;
    @JsonBackReference
    private List<ServiceReportDto> serviceReports;

    public boolean isHaveHistory() {
        return serviceReports.size()>0;
    }
}
