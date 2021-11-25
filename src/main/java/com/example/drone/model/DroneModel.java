package com.example.drone.model;

import com.example.drone.dto.DroneRequest;
import com.example.drone.util.Const;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;

@Data
@Entity
@Table(name = "drone_model")
@AllArgsConstructor
public class DroneModel implements Serializable {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    long id;
    String serialNumber, model, state;
    float batteryCapacity, weight, availableWeight;

    public DroneModel(){}
    public DroneModel(DroneRequest request, Const.DRONE_STATE state, float availableWeight){
        serialNumber = request.getSerialNumber();
        weight = request.getWeight();
        batteryCapacity = request.getBatteryCapacity();
        model = request.getModel();
        this.state = state.toString();
        this.availableWeight = availableWeight;
    }

    public DroneModel(String serialNumber, String model, String state, float weight, float availableWeight, float batteryCapacity){
        this.serialNumber = serialNumber;
        this.weight = weight;
        this.batteryCapacity = batteryCapacity;
        this.model = model;
        this.state = state;
        this.availableWeight = availableWeight;
    }
}
