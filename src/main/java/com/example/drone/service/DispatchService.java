package com.example.drone.service;

import com.example.drone.dto.DroneRequest;
import com.example.drone.dto.GenericResponse;
import com.example.drone.model.DroneModel;
import com.example.drone.repository.DroneRepository;
import com.example.drone.util.Const;
import com.example.drone.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class DispatchService {
    @Autowired
    DroneRepository droneRepository;
    public GenericResponse logDrone(DroneRequest request){

        //validate Drone properties
        String validate = Validation.validateLogDrone(request);
        if(null != validate)
            return new GenericResponse(Const.RESPONSECODE[1],validate, Const.FAILED);

        //building Drone model
        DroneModel droneModel = new DroneModel(request, Const.DRONE_STATE.IDLE );

        try {
            //saving drone on database
            droneRepository.save(droneModel);
            return new GenericResponse(Const.RESPONSECODE[0], "saved", Const.SUCCESSFUL);
        }
        catch (DuplicateKeyException dataEx){
            return new GenericResponse(Const.RESPONSECODE[1], "Duplicate serial number", Const.FAILED);
        }
        catch (Exception ex){
            return new GenericResponse(Const.RESPONSECODE[1], ex.getMessage(), Const.FAILED);
        }

    }
}
