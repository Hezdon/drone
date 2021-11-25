package com.example.drone.model;

import com.example.drone.dto.MedicationRequest;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "medication")
public class MedicationModel {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    long id;
    String name, code, image;
    float weight;

    public MedicationModel(){}
    public MedicationModel(MedicationRequest request){
        this.name = request.getName();
        this.code = request.getCode();
        this.weight = request.getWeight();
        this.image = getImage();
    }
}
