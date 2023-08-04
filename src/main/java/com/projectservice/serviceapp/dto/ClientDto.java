package com.projectservice.serviceapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientDto {

    private Integer id;
    private String name;
    private String lastName;
    private String phoneNo;
    private String email;
    private String address;
    @JsonIgnore
    private List<ServiceReportDto> serviceReports;
}
