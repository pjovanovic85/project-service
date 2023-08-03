package com.projectservice.serviceapp.service;

import com.projectservice.serviceapp.GenericSpecification;
import com.projectservice.serviceapp.Mapper;
import com.projectservice.serviceapp.ServiceReportMapper;
import com.projectservice.serviceapp.dto.ServiceReportDto;
import com.projectservice.serviceapp.model.*;
import com.projectservice.serviceapp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ServiceReportService {

    @Autowired private ServiceReportRepository serviceReportRepository;
    @Autowired private ClientRepository clientRepository;
    @Autowired private DeviceRepository deviceRepository;
    @Autowired private ServiceReportStatusRepository statusRepository;
    @Autowired private GenericSpecification genericSpecification;
    @Autowired private SparePartRepository sparePartRepository;
    @Autowired private ServiceReportMapper mapper;
    @Autowired private Mapper genericMapper;

    public Page<ServiceReportDto> findAll(Pageable pageable, Map<String, Object> params) {
        Page all = serviceReportRepository.findAll(genericSpecification.hasNestedParameter(params), pageable);
        List<ServiceReportDto> dtoList = mapper.mapToDtoList((List<ServiceReport>) all.getContent());

        return new PageImpl<>(dtoList, all.getPageable(), all.getTotalElements());
    }

    public ServiceReportDto getById(Integer id) {
        ServiceReport serviceReport = serviceReportRepository.findById(id).orElse(null);

        return mapper.mapToDto(serviceReport);
    }

    public ServiceReportDto save(ServiceReportDto serviceReportDto) {
        serviceReportDto.setReceiptDate(Date.valueOf(LocalDate.now()));
        ServiceReport serviceReportForSave = (ServiceReport) genericMapper.mapToEntity(serviceReportDto, ServiceReport.class);
        List<ServiceReportStatus> statusList = new ArrayList<>();
        ServiceReportStatus reportStatus = new ServiceReportStatus();
        reportStatus.setModifyDate(Date.valueOf(LocalDate.now()));
        reportStatus.setStatusCode(ServiceStatusEnum.ON_FRONT.getStatusCode());
        reportStatus.setDescription(ServiceStatusEnum.ON_FRONT.getDescription());
        reportStatus.setServiceReport(serviceReportForSave);
//        ServiceReportStatus savedStatus = statusRepository.save(reportStatus);
        statusList.add(reportStatus);
        serviceReportForSave.setStatusList(statusList);
        serviceReportForSave.setCurrentStatusCode(reportStatus.getStatusCode());
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

        return mapper.mapToDto(savedServiceReport);
    }

    public ServiceReportDto updateStatus(Integer id, int statusCode) {
        ServiceReport serviceReport = serviceReportRepository.findById(id).orElse(null);
        if (serviceReport.getCurrentStatusCode() == statusCode){
            //TODO: implement exception to handle status
//            throw new Exception("already in that status");
        }
        ServiceReportStatus reportStatus = new ServiceReportStatus();
        reportStatus.setModifyDate(Date.valueOf(LocalDate.now()));
        reportStatus.setServiceReport(serviceReport);
        switch (statusCode) {
            case 1:
                reportStatus.setStatusCode(ServiceStatusEnum.ON_FRONT.getStatusCode());
                reportStatus.setDescription(ServiceStatusEnum.ON_FRONT.getDescription());
                break;
            case 2:
                reportStatus.setStatusCode(ServiceStatusEnum.IN_SERVICE.getStatusCode());
                reportStatus.setDescription(ServiceStatusEnum.IN_SERVICE.getDescription());
                break;
            case 3:
                reportStatus.setStatusCode(ServiceStatusEnum.ON_WORK.getStatusCode());
                reportStatus.setDescription(ServiceStatusEnum.ON_WORK.getDescription());
                break;
            case 4:
                reportStatus.setStatusCode(ServiceStatusEnum.RELEASED.getStatusCode());
                reportStatus.setDescription(ServiceStatusEnum.RELEASED.getDescription());
                serviceReport.setCheckOutDate(reportStatus.getModifyDate());
                break;
        }
        serviceReport.getStatusList().add(reportStatus);
        serviceReport.setCurrentStatusCode(statusCode);

        ServiceReport updatedServiceReport = serviceReportRepository.save(serviceReport);

        return mapper.mapToDto(updatedServiceReport);
    }

    public ServiceReportDto updateWithServiceIntervention(Integer serviceReportId, Map<Integer, Integer> sparePartMap,
                                                          String serviceDescription){
        ServiceReport serviceReportForUpdate = serviceReportRepository.getReferenceById(serviceReportId);
        List<ServiceReportSparePart> sRSparePartList = new ArrayList<ServiceReportSparePart>();
        for (Map.Entry<Integer, Integer> sparePartCount: sparePartMap.entrySet()) {
            SparePart sparePart = sparePartRepository.findById(sparePartCount.getKey()).orElse(null);
            ServiceReportSparePart serviceReportSparePart = new ServiceReportSparePart(serviceReportForUpdate,
                    sparePart, sparePartCount.getValue());
            sRSparePartList.add(serviceReportSparePart);
        }
        serviceReportForUpdate.setServiceReportSpareParts(sRSparePartList);
        serviceReportForUpdate.setServiceDescription(serviceDescription);

        ServiceReport updatedServiceReport = serviceReportRepository.save(serviceReportForUpdate);

        return  mapper.mapToDto(updatedServiceReport);
    }



    private void addSparePart(ServiceReport serviceReport, SparePart sparePart, int count) {
        ServiceReportSparePart serviceReportSparePart = new ServiceReportSparePart(serviceReport, sparePart, count);
        serviceReport.getServiceReportSpareParts().add(serviceReportSparePart);

        sparePart.getServiceReportSpareParts().add(serviceReportSparePart);
    }
}