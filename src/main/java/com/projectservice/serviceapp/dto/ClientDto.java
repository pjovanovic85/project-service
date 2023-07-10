package com.projectservice.serviceapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {

    private Integer id;
    private String name;
    private String lastName;
    private String phoneNo;
    private String email;
    private String address;
}
