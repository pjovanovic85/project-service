package com.projectservice.serviceapp.controller;

import com.projectservice.serviceapp.dto.ServiceReportDto;
import com.projectservice.serviceapp.service.ServiceReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/service-report")
public class ServiceReportController {

    @Autowired
    private ServiceReportService serviceReportService;

    @GetMapping("/all")
    public ResponseEntity<?> findAll(@RequestParam(defaultValue = "0") int pageNumber,
                                     @RequestParam(defaultValue = "15") int pageSize,
                                     @RequestParam(defaultValue = "desc") String sortOrder,
                                     @RequestParam(defaultValue = "id") String sortField,
                                     @RequestParam Map<String, Object> params) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortField);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        return new ResponseEntity<>(serviceReportService.findAll(pageable, params), HttpStatus.OK);
    }

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

    @PutMapping("/update-service-intervention")
    public ResponseEntity<?> updateDeviceStatus(@RequestParam Integer serviceReportId,
                                                @RequestParam String serviceDescription,
                                                @RequestBody Map<Integer, Integer> sparePartMap){

        return new ResponseEntity<>(serviceReportService.updateWithServiceIntervention(serviceReportId, sparePartMap, serviceDescription), HttpStatus.OK);
    }
}
