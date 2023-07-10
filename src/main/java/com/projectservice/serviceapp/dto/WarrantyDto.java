package com.projectservice.serviceapp.dto;

import com.projectservice.serviceapp.model.Device;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;
import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WarrantyDto {

    private Integer warrantyId;
    private Date purchaseDate;
    private int warrantyPeriod;
    private Blob picture;
    private Device device;
}
