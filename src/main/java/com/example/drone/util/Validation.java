package com.example.drone.util;

import com.example.drone.dto.DroneRequest;
import com.example.drone.dto.MedicationRequest;
import com.example.drone.model.DroneModel;


public class Validation {

    public static String validateLogDrone(DroneRequest request){
        if(request.getSerialNumber().length() > 100)
            return "Drone serial number can not be more than 100 characters";
        if(!Const.DRONE_MODEL.contains(request.getModel()))
            return "Drone model undefined";
        if(100 < request.getBatteryCapacity())
            return "Drone battery capacity can not be more than 100 percent";
        if(500 < request.getWeight())
            return "Drone weight can not be more than 500 grams";

        return null;

    }

    public static String validateLogMedication(MedicationRequest request){
        if(!request.getName().matches("^[A-Za-z0-9_-]*$"))
            return "Medication name can only contain letters, numbers, ‘-‘, ‘_’";
        if(!request.getCode().matches("^[A-Z0-9_]*$"))
            return "Medication code can only contain upper case letters, numbers, ‘_’";
        if(0.01f > request.getWeight())
            return "Medication weight can not be less than 0.01 gram";

        return null;

    }

    public static DroneModel moveDroneToNextState(DroneModel droneModel){
        if(Const.DRONE_STATE.LOADING.toString().equalsIgnoreCase(droneModel.getState())){
            droneModel.setState(Const.DRONE_STATE.LOADED.toString());
            return droneModel;
        }

        if(Const.DRONE_STATE.LOADED.toString().equalsIgnoreCase(droneModel.getState())){
            droneModel.setState(Const.DRONE_STATE.DELIVERING.toString());
            droneModel.setBatteryCapacity(droneModel.getBatteryCapacity() - Const.REDUCE_BATTERY_LEVEL);
            return droneModel;
        }

        if(Const.DRONE_STATE.DELIVERING.toString().equalsIgnoreCase(droneModel.getState())){
            droneModel.setState(Const.DRONE_STATE.DELIVERED.toString());
            return droneModel;
        }

        if(Const.DRONE_STATE.DELIVERED.toString().equalsIgnoreCase(droneModel.getState())){
            droneModel.setState(Const.DRONE_STATE.RETURNING.toString());
            droneModel.setAvailableWeight(droneModel.getWeight());
            droneModel.setBatteryCapacity(droneModel.getBatteryCapacity() - Const.REDUCE_BATTERY_LEVEL);
            return droneModel;
        }

        if(Const.DRONE_STATE.RETURNING.toString().equalsIgnoreCase(droneModel.getState())){
            droneModel.setState(Const.DRONE_STATE.IDLE.toString());
            return droneModel;
        }

        return droneModel;
    }
}
