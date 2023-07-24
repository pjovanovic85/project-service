package com.projectservice.serviceapp.repository;

import com.projectservice.serviceapp.model.ServiceReportStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceReportStatusRepository extends JpaRepository<ServiceReportStatus, Integer> {
}
