package com.projectservice.serviceapp;

import com.projectservice.serviceapp.dto.ServiceReportDto;
import com.projectservice.serviceapp.dto.SparePartDto;
import com.projectservice.serviceapp.model.ServiceReport;
import com.projectservice.serviceapp.model.ServiceReportSparePart;
import com.projectservice.serviceapp.model.SparePart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ServiceReportMapper {

    @Autowired
    private Mapper mapper;

    public ServiceReportDto mapToDto(ServiceReport serviceReport) {
        ServiceReportDto dto = (ServiceReportDto) mapper.mapToDto(serviceReport, ServiceReportDto.class);

        List<ServiceReportSparePart> serviceReportSpareParts = serviceReport.getServiceReportSpareParts();
        if (serviceReportSpareParts != null){
            List<SparePartDto> sparePartDtoList = serviceReportSpareParts.stream()
                    .map(sparePartCount -> {
                        SparePart sparePart = sparePartCount.getServiceReportSparePartId().getSparePart();
                        SparePartDto sparePartDto = (SparePartDto) mapper.mapToDto(sparePart, SparePartDto.class);
                        sparePartDto.setUsedQuantity(sparePartCount.getCount());
                        return sparePartDto;
                    })
                    .collect(Collectors.toList());
            dto.setSparePartList(sparePartDtoList);
        }

        return dto;
    }

    public List<ServiceReportDto> mapToDtoList(List<ServiceReport> serviceReportList) {
        return serviceReportList.stream().map(entity -> mapToDto(entity)).collect(Collectors.toList());
    }
}
