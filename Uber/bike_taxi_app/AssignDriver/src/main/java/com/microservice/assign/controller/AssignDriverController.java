package com.microservice.assign.controller;

import com.microservice.assign.model.FromTo;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/uber/driver")
public class AssignDriverController {

    @PostMapping("/assign")
    public Object assignDriver(@Valid @RequestBody FromTo fromTo){


        return "";
    }
}
