package com.example.drone.dbInterface;

import com.example.drone.model.MedicationModel;

import java.util.List;

public interface DbQueryInf {
    List<MedicationModel> findAllMedicationbyDrone(long droneId, String status);
    void updateMedicationAndDroneLoadStatus(long droneId);
}
