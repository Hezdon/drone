package com.example.drone.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class DroneRequest {

    @NotBlank(message = "Serial number can not be empty")
    String serialNumber;
    @NotBlank(message = "Model can not be empty")
    String model;
    @NotBlank(message = "Weight can not be empty")
    int weight;
    @NotBlank(message = "Battery can not be empty")
    float batteryCapacity;
}
