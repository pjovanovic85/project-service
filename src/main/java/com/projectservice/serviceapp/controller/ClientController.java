package com.projectservice.serviceapp.controller;

import com.projectservice.serviceapp.dto.ClientDto;
import com.projectservice.serviceapp.model.Client;
import com.projectservice.serviceapp.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllClients(@RequestParam(defaultValue = "0") int pageNumber,
                                                @RequestParam(defaultValue = "15") int pageSize,
                                                @RequestParam(defaultValue = "desc") String sortOrder,
                                                @RequestParam(defaultValue = "id") String sortField,
                                                @RequestParam Map<String, String> params) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortField);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<ClientDto> allClientsPaged = clientService.getAllClients(pageable, params);

        return new ResponseEntity<>(allClientsPaged, HttpStatus.OK);
    }

    @GetMapping("/id")
    public ResponseEntity<?> getClientById(@RequestParam Integer id) {
        ClientDto clientDto = clientService.getClientById(id);

        return new ResponseEntity<>(clientDto, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ClientDto saveClient(@RequestBody ClientDto clientDto){

        return clientService.saveClient(clientDto);
    }
}
