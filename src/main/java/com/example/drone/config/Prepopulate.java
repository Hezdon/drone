package com.example.drone.config;

import com.example.drone.model.DroneModel;
import com.example.drone.model.MedicationModel;
import com.example.drone.repository.DroneRepository;
import com.example.drone.repository.MedicationRespository;
import com.example.drone.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class Prepopulate {
    @Autowired
    DroneRepository droneRepository;

    @Autowired
    MedicationRespository medicationRespository;

    @PostConstruct
    public void uploadDrone(){
        List<DroneModel> drones = new ArrayList<>();
        drones.add(new DroneModel("123456", Const.DRONE_MODEL.get(0), Const.DRONE_STATE.IDLE.toString(), 100.00f, 85.00f));
        drones.add(new DroneModel("123457", Const.DRONE_MODEL.get(1), Const.DRONE_STATE.IDLE.toString(), 200.20f, 75.00f));
        drones.add(new DroneModel("123458", Const.DRONE_MODEL.get(2), Const.DRONE_STATE.IDLE.toString(), 300.90f, 100.00f));
        drones.add(new DroneModel("123459", Const.DRONE_MODEL.get(3), Const.DRONE_STATE.IDLE.toString(), 400.25f, 20.00f));
        drones.add(new DroneModel("1234510", Const.DRONE_MODEL.get(0), Const.DRONE_STATE.IDLE.toString(), 500.40f, 65.00f));
        drones.add(new DroneModel("1234511", Const.DRONE_MODEL.get(1), Const.DRONE_STATE.IDLE.toString(), 400.60f, 55.00f));
        drones.add(new DroneModel("1234512", Const.DRONE_MODEL.get(2), Const.DRONE_STATE.IDLE.toString(), 300.00f, 45.00f));
        drones.add(new DroneModel("1234512", Const.DRONE_MODEL.get(3), Const.DRONE_STATE.IDLE.toString(), 200.10f, 35.00f));

        droneRepository.saveAll(drones);
    }

    @PostConstruct
    public void uploadMedication(){
        List<MedicationModel> medications = new ArrayList<>();
        medications.add(new MedicationModel("dorre", "MED123",  null, 500.00f));
        medications.add(new MedicationModel("Dorre-124", "MED124_CX",  null, 100.00f));
        medications.add(new MedicationModel("Dorre_CX", "MED125_CX",  null, 185.00f));
        medications.add(new MedicationModel("dorre-118", "MED126_CX",  null, 255.00f));
        medications.add(new MedicationModel("dorre-119", "MED127_CX",  null, 35.00f));
        medications.add(new MedicationModel("dorre-120", "MED128_CX",  null, 95.00f));
        medications.add(new MedicationModel("dorre-121", "MED129_CX",  null, 455.00f));
        medications.add(new MedicationModel("Dorre_QX", "MED_QUINE",  null, 315.00f));


        medicationRespository.saveAll(medications);
    }
}