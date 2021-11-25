package com.example.drone.model;

import com.example.drone.util.Const;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "drone_load")
@Data
public class DroneLoad implements Serializable {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    long id;
    long droneId;
    long medicationId;
    float medicationWeight;
    String status;

    public DroneLoad(DroneModel droneModel, MedicationModel medicationModel){
        droneId = droneModel.getId();
        medicationId = medicationModel.getId();
        medicationWeight = medicationModel.getWeight();
        status = Const.ACTIVE;
    }
}
