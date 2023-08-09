package com.projectservice.serviceapp.model;

import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class ServiceReportSparePart {

    @EmbeddedId
    private ServiceReportSparePartId serviceReportSparePartId = new ServiceReportSparePartId();
    private int count;

    public ServiceReportSparePart(ServiceReport serviceReport, SparePart sparePart, int count) {
        serviceReportSparePartId.setServiceReport(serviceReport);
        serviceReportSparePartId.setSparePart(sparePart);
        this.count = count;
    }


}