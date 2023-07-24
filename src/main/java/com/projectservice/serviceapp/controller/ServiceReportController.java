package com.projectservice.serviceapp.controller;

import com.projectservice.serviceapp.dto.DeviceDto;
import com.projectservice.serviceapp.dto.ServiceReportDto;
import com.projectservice.serviceapp.model.Device;
import com.projectservice.serviceapp.model.ServiceReport;
import com.projectservice.serviceapp.service.ServiceReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/service-report")
public class ServiceReportController {

    @Autowired
    private ServiceReportService serviceReportService;

    @GetMapping("/id")
    public ResponseEntity<?> getById(@RequestParam Integer id){

        return new ResponseEntity<>(serviceReportService.getById(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveDevice(@RequestBody ServiceReportDto serviceReportDto){

        return new ResponseEntity<>(serviceReportService.save(serviceReportDto), HttpStatus.OK);
    }

    @PostMapping("/update-status")
    public ResponseEntity<?> updateDeviceStatus(@RequestParam Integer id, @RequestParam Integer statusCode){

        return new ResponseEntity<>(serviceReportService.updateStatus(id, statusCode), HttpStatus.OK);
    }
}
