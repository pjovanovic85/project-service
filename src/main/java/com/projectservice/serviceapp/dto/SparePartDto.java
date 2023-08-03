package com.projectservice.serviceapp.dto;

import com.projectservice.serviceapp.model.ServiceReportSparePart;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
