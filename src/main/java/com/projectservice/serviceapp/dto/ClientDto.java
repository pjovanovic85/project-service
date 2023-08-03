package com.projectservice.serviceapp.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference
    private List<ServiceReportDto> serviceReports;
}
