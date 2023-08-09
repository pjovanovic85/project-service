package com.projectservice.serviceapp.service;

import com.projectservice.serviceapp.GenericSpecification;
import com.projectservice.serviceapp.Mapper;
import com.projectservice.serviceapp.dto.DeviceDto;
import com.projectservice.serviceapp.model.Device;
import com.projectservice.serviceapp.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DeviceService {

    @Autowired
    private Mapper mapper;

    @Autowired
    private GenericSpecification genericSpecification;

    @Autowired
    private DeviceRepository deviceRepository;

    public List<DeviceDto> getAllDevices(){
        return mapper.mapToDtoList(deviceRepository.findAll(), DeviceDto.class);
    }

    public DeviceDto findById(Integer id){
        Optional<Device> byId = deviceRepository.findById(id);
        if (byId.isPresent()) {
            return (DeviceDto) mapper.mapToDto(byId.get(), DeviceDto.class);
        } else {
            throw new NoSuchElementException();
        }
    }

    public List<DeviceDto> getAllDevices(Map<String, String> params){
        List<Device> devices = deviceRepository.findAll(genericSpecification.hasParameter(params));

        return mapper.mapToDtoList(devices, DeviceDto.class);
    }

    public DeviceDto saveDevice(DeviceDto deviceDto){
        Device savedDevice = deviceRepository.save((Device) mapper.mapToEntity(deviceDto, Device.class));

        return (DeviceDto) mapper.mapToDto(savedDevice, DeviceDto.class);
    }
}
