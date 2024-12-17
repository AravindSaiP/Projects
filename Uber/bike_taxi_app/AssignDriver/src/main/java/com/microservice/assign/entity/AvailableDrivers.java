package com.microservice.assign.entity;

import com.microservice.assign.Availability.RidersAvailability;
import jakarta.persistence.Entity;
import lombok.Data;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Entity
@Data
public class AvailableDrivers {

    private String driverName;
    private String location;

    @Va
    private String position;
}
