package com.projectservice.serviceapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Lob;
import java.sql.Blob;
import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
}
