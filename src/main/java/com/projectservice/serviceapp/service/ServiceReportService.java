package com.projectservice.serviceapp.service;

import com.projectservice.serviceapp.GenericSpecification;
import com.projectservice.serviceapp.Mapper;
import com.projectservice.serviceapp.dto.ServiceReportDto;
import com.projectservice.serviceapp.model.*;
import com.projectservice.serviceapp.repository.ClientRepository;
import com.projectservice.serviceapp.repository.DeviceRepository;
import com.projectservice.serviceapp.repository.ServiceReportRepository;
import com.projectservice.serviceapp.repository.ServiceReportStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class ServiceReportService {

    @Autowired private ServiceReportRepository serviceReportRepository;
    @Autowired private ClientRepository clientRepository;
    @Autowired private DeviceRepository deviceRepository;
    @Autowired private ServiceReportStatusRepository statusRepository;
    @Autowired private GenericSpecification genericSpecification;
    @Autowired private Mapper mapper;

    public Page<ServiceReportDto> findAll(Pageable pageable, Map<String, String> params) {
        Page all = serviceReportRepository.findAll(genericSpecification.hasParameter(params), pageable);
        List<ServiceReportDto> dtoList = mapper.mapToDtoList(all.getContent(), ServiceReportDto.class);

        return new PageImpl<>(dtoList, all.getPageable(), all.getTotalElements());
    }

    public ServiceReportDto getById(Integer id) {
        ServiceReport serviceReport = serviceReportRepository.findById(id).orElse(null);

        return (ServiceReportDto) mapper.mapToDto(serviceReport, ServiceReportDto.class);
    }

    public ServiceReportDto save(ServiceReportDto serviceReportDto) {
        serviceReportDto.setReceiptDate(Date.valueOf(LocalDate.now()));
        ServiceReport serviceReportForSave = (ServiceReport) mapper.mapToEntity(serviceReportDto, ServiceReport.class);
        ServiceReportStatus reportStatus = new ServiceReportStatus();
        reportStatus.setModifyDate(Date.valueOf(LocalDate.now()));
        reportStatus.setStatusCode(ServiceStatusEnum.ON_FRONT.getStatusCode());
        reportStatus.setDescription(ServiceStatusEnum.ON_FRONT.getDescription());
        reportStatus.setServiceReport(serviceReportForSave);
        serviceReportForSave.setStatus(reportStatus);
        Device device = serviceReportForSave.getDevice();
        Client client = serviceReportForSave.getClient();
        if (client.getId() == null) {
            Client savedClient = clientRepository.save(client);
            serviceReportForSave.setClient(savedClient);
        }
        if (device.getId() == null) {
            Device savedDevice = deviceRepository.save(device);
            serviceReportForSave.setDevice(savedDevice);
        }
        ServiceReport savedServiceReport = serviceReportRepository.save(serviceReportForSave);

        return (ServiceReportDto) mapper.mapToDto(savedServiceReport, ServiceReportDto.class);
    }

    public ServiceReportDto updateStatus(Integer id, int statusCode) {
        ServiceReport serviceReport = serviceReportRepository.findById(id).orElse(null);
        ServiceReportStatus reportStatus = new ServiceReportStatus();
        reportStatus.setModifyDate(Date.valueOf(LocalDate.now()));
        switch (statusCode) {
            case 1:
                reportStatus.setStatusCode(ServiceStatusEnum.ON_FRONT.getStatusCode());
                reportStatus.setDescription(ServiceStatusEnum.ON_FRONT.getDescription());
            case 2:
                reportStatus.setStatusCode(ServiceStatusEnum.IN_SERVICE.getStatusCode());
                reportStatus.setDescription(ServiceStatusEnum.IN_SERVICE.getDescription());
            case 3:
                reportStatus.setStatusCode(ServiceStatusEnum.ON_WORK.getStatusCode());
                reportStatus.setDescription(ServiceStatusEnum.ON_WORK.getDescription());
            case 4:
                reportStatus.setStatusCode(ServiceStatusEnum.RELEASED.getStatusCode());
                reportStatus.setDescription(ServiceStatusEnum.RELEASED.getDescription());
        }
        ServiceReportStatus savedReportStatus = statusRepository.save(reportStatus);
        serviceReport.setStatus(savedReportStatus);
        if (savedReportStatus.getStatusCode() == 4){
            serviceReport.setCheckOutDate(savedReportStatus.getModifyDate());
        }
        serviceReportRepository.save(serviceReport);

        return (ServiceReportDto) mapper.mapToDto(serviceReport, ServiceReportDto.class);
    }
}
