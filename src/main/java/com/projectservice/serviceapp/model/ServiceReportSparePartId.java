package com.projectservice.serviceapp.model;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ServiceReportSparePartId implements Serializable {

    @ManyToOne private ServiceReport serviceReport;
    @ManyToOne private SparePart sparePart;
}
