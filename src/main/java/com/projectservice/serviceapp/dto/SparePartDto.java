package com.projectservice.serviceapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SparePartDto {


    private Integer id;
    private String serialNo;
    private String name;
    private String description;
    private int availableQuantity;
    private int usedQuantity;
    private Double price;
    private String location;
}
