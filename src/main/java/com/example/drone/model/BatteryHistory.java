package com.example.drone.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "battery_history")
public class BatteryHistory implements Serializable {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    long id;
    long droneId;
    String serialNumber, state;
    float batteryCapacity;
    Date createdOn;
    public BatteryHistory(){}
    public BatteryHistory(DroneModel droneModel){
        droneId = droneModel.getId();
        serialNumber = droneModel.getSerialNumber();
        batteryCapacity = droneModel.getBatteryCapacity();
        state = droneModel.getState();
        createdOn = new Date();
    }
}
