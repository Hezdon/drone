package com.example.drone.util;

import com.example.drone.dto.DroneRequest;


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
}
