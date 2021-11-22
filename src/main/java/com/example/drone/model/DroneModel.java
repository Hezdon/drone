package com.example.drone.model;

import com.example.drone.dto.DroneRequest;
import com.example.drone.util.Const;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Collections;

@Data
@Entity
@Table(name = "drone_model")
public class DroneModel {
    @SequenceGenerator(name = "drone_number", sequenceName = "drone_number")
    @Id
    long id;
    String serialNumber, model, state;
    int weight;
    float batteryCapacity;

    public DroneModel(DroneRequest request, Const.DRONE_STATE state){
        serialNumber = request.getSerialNumber();
        weight = request.getWeight();
        batteryCapacity = request.getBatteryCapacity();
        model = request.getModel();
        this.state = state.toString();
    }
}
