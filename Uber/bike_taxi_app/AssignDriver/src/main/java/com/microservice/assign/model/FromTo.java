package com.microservice.assign.model;

import jakarta.validation.constraints.NotBlank;

public class FromTo {
    @NotBlank
    private String from;
    @NotBlank
    private String to;

}
