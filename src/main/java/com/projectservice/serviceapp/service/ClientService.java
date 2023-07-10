package com.projectservice.serviceapp.service;

import com.projectservice.serviceapp.Mapper;
import com.projectservice.serviceapp.dto.ClientDto;
import com.projectservice.serviceapp.model.Client;
import com.projectservice.serviceapp.repository.ClientRepository;
import com.projectservice.serviceapp.GenericSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private Mapper mapper;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    GenericSpecification genericSpecification;

    public List<ClientDto> getAllClients() {
        List<Client> all = clientRepository.findAll();

        return mapper.mapToDtoList(all, ClientDto.class);
    }

    public Page<ClientDto> getAllClientsPaged(Pageable pageable) {
        Page<Client> all = clientRepository.findAll(pageable);
        List<ClientDto> dtoList = mapper.mapToDtoList(all.getContent(), ClientDto.class);

        return new PageImpl<>(dtoList, all.getPageable(), all.getTotalElements());
    }

    public Client getClientById(Integer id) {
        Optional<Client> byId = clientRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        } else {
            throw new NoSuchElementException();
        }
    }

    public List<ClientDto> getClientsByParams(Map<String, String> params){
        List<Client> clients = clientRepository.findAll(genericSpecification.hasParameter(params));

        return mapper.mapToDtoList(clients, ClientDto.class);
    }

    public ClientDto saveClient(ClientDto clientDto) {
        Client clientForSave = (Client) mapper.mapToEntity(clientDto, Client.class);
        Client savedClient = clientRepository.save(clientForSave);

        return (ClientDto) mapper.mapToDto(savedClient, ClientDto.class);
    }

}
