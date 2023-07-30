package com.projectservice.serviceapp.controller;

import com.projectservice.serviceapp.dto.ClientDto;
import com.projectservice.serviceapp.dto.SparePartDto;
import com.projectservice.serviceapp.model.Client;
import com.projectservice.serviceapp.service.SparePartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/spare-part")
public class SparePartController {

    @Autowired
    private SparePartService sparePartService;

    @GetMapping("/all")
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0") int pageNumber,
                                    @RequestParam(defaultValue = "15") int pageSize,
                                    @RequestParam(defaultValue = "desc") String sortOrder,
                                    @RequestParam(defaultValue = "id") String sortField,
                                    @RequestParam Map<String, String> params) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortField);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<SparePartDto> allSparePartsPaged = sparePartService.getAll(pageable, params);

        return new ResponseEntity<>(allSparePartsPaged, HttpStatus.OK);
    }

    @GetMapping("/id")
    public ResponseEntity<?> getSparePartById(@RequestParam Integer id) {
        SparePartDto sparePartDto = sparePartService.getById(id);

        return new ResponseEntity<>(sparePartDto, HttpStatus.OK);
    }

    @PostMapping("/save")
    public SparePartDto saveSparePart(@RequestBody SparePartDto sparePartDto){

        return sparePartService.save(sparePartDto);
    }
}
