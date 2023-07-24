package com.projectservice.serviceapp.repository;

import com.projectservice.serviceapp.model.ServiceReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceReportRepository extends JpaRepository<ServiceReport, Integer>, JpaSpecificationExecutor<ServiceReport> {
}
