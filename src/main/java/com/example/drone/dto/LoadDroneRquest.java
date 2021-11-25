package com.example.drone.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoadDroneRquest {
    @NotBlank(message = "Serial number can not be empty")
    String droneSerialNumber;
    @NotBlank(message = "Medication code can not be empty")
    String medicationCode;
}
