package com.projectservice.serviceapp.dto;

import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SparePartDto {


    private Integer id;
    private String serialNo;
    private String name;
    private String description;
    private List<ServiceReportDto> serviceReportList;
}
