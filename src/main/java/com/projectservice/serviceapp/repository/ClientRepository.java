package com.projectservice.serviceapp.repository;

import com.projectservice.serviceapp.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer>, JpaSpecificationExecutor<Client> {

}
