package com.projectservice.serviceapp.repository;

import com.projectservice.serviceapp.model.SparePart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SparePartRepository extends JpaRepository<SparePart,Integer>, JpaSpecificationExecutor<SparePart> {
}
