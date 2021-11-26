package com.example.drone.config;

import com.example.drone.model.DroneModel;
import com.example.drone.service.DispatchService;
import com.example.drone.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration
@EnableScheduling
public class AppScheduler {
    @Autowired
    DispatchService dispatchService;
    @Scheduled(fixedDelay = 3000) // run every 5 mins
    public void periodicTask() {
        List<DroneModel> droneModels = dispatchService.findAllDrone();

        droneModels.forEach(droneModel -> {
            droneModel = Validation.moveDroneToNextState(droneModel);
            dispatchService.batteryCheck(droneModel);
        });
    }
}
