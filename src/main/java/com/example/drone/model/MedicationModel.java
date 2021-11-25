package com.example.drone.model;

import com.example.drone.dto.MedicationRequest;
import com.example.drone.util.Const;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "medication")
public class MedicationModel implements Serializable {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    long id;
    String name, code, image, status;
    float weight;

    public MedicationModel(){}
    public MedicationModel(String name, String code, String image, float weight, String state){
        this.name = name;
        this.code = code;
        this.weight = weight;
        this.image = image;
        status = state;
    }

    public MedicationModel(MedicationRequest request){
        this.name = request.getName();
        this.code = request.getCode();
        this.weight = request.getWeight();
        this.image = getImage();
        status = Const.MEDICATION_STATE.AVAILABLE.toString();
    }
}
