package com.example.drone.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "medication_model")
public class MedicationModel {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "drone_number", sequenceName = "drone_number")
    @Id
    long id;
    String name, code, image;
    float weight;

    public MedicationModel(String name, String code, String image, float weight){
        this.name = name;
        this.code = code;
        this.weight = weight;
        this.image = image;
    }
}
