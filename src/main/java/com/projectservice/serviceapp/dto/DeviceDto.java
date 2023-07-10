package com.projectservice.serviceapp.dto;

import com.projectservice.serviceapp.model.Warranty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceDto {

    private Integer deviceId;
    private String deviceGroup;
    private String manufacturer;
    private String model;
    private String serialNumber;
    private String additionalParam;
    private Warranty warranty;
}
