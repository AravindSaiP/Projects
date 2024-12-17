package com.microservices.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
public class DestinationModel implements Serializable {

    @Email
    @JsonIgnore
    private String email;

    @NotBlank(message = "Pick up point is necessary")
    private String from;

    @NotBlank(message = "Destination is necessary")
    private String to;
}
