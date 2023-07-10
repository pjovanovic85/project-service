package com.projectservice.serviceapp.controller;

import com.projectservice.serviceapp.dto.ClientDto;
import com.projectservice.serviceapp.dto.DeviceDto;
import com.projectservice.serviceapp.model.Client;
import com.projectservice.serviceapp.model.Device;
import com.projectservice.serviceapp.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping(value = "/id", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDeviceById(@RequestParam Integer id) {
        Device device = deviceService.findById(id);

        return new ResponseEntity<>(device, HttpStatus.OK);
    }

    @PostMapping("/save")
    public Device saveDevice(@RequestBody DeviceDto deviceDto){

        return deviceService.saveDevice(deviceDto);
    }
}
