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

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping(value = "/id", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDeviceById(@RequestParam Integer id) {
        DeviceDto deviceDto = deviceService.findById(id);

        return new ResponseEntity<>(deviceDto, HttpStatus.OK);
    }

    @GetMapping(value = "/params", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDeviceByParam(@RequestParam Map<String, String> params) {
        List<DeviceDto> deviceDto = deviceService.findBySerialNumber(params);

        return new ResponseEntity<>(deviceDto, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveDevice(@RequestBody DeviceDto deviceDto){
        DeviceDto savedDeviceDto = deviceService.saveDevice(deviceDto);

        return new ResponseEntity<>(savedDeviceDto, HttpStatus.OK);
    }
}
