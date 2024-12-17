package com.microservice.assign.repository;

import com.microservice.assign.entity.AvailableDrivers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignDriver extends JpaRepository<AvailableDrivers,Long> {
}
