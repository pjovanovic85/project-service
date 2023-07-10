package com.projectservice.serviceapp.service;

import com.projectservice.serviceapp.Mapper;
import com.projectservice.serviceapp.dto.DeviceDto;
import com.projectservice.serviceapp.model.Client;
import com.projectservice.serviceapp.model.Device;
import com.projectservice.serviceapp.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DeviceService {

    @Autowired
    private Mapper mapper;

    @Autowired
    private DeviceRepository deviceRepository;

    public List<Device> getAllDevices(){
        return deviceRepository.findAll();
    }

    public Device findById(Integer id){
        Optional<Device> byId = deviceRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        } else {
            throw new NoSuchElementException();
        }
    }

    public Device saveDevice(DeviceDto deviceDto){
        Device deviceForSave = (Device) mapper.mapToEntity(deviceDto, Device.class);

        return deviceRepository.save(deviceForSave);
    }
}
