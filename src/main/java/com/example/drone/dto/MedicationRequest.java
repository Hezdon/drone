package com.example.drone.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class MedicationRequest {

    @NotBlank(message = "Medication name can not be empty")
    String name;
    @NotBlank(message = "Medication weight can not be empty")
    float weight;
    @NotBlank(message = "Medication code can not be empty")
    String code;
    String image ;
}
