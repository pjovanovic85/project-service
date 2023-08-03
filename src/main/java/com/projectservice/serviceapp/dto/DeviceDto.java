package com.projectservice.serviceapp.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.projectservice.serviceapp.model.ServiceReport;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Lob;
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
}
