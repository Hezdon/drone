package com.example.drone.service;

import com.example.drone.dto.DroneRequest;
import com.example.drone.dto.GenericResponse;
import com.example.drone.dto.MedicationRequest;
import com.example.drone.model.DroneModel;
import com.example.drone.model.MedicationModel;
import com.example.drone.repository.DroneRepository;
import com.example.drone.repository.MedicationRespository;
import com.example.drone.util.Const;
import com.example.drone.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DispatchService {
    @Autowired
    DroneRepository droneRepository;

    @Autowired
    MedicationRespository medicationRespository;
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

    public List<DroneModel> findAllDrone(){
        return droneRepository.findAll();
    }

    public GenericResponse logMedication(MedicationRequest request){

        //validate Medication properties
        String validate = Validation.validateLogMedication(request);
        if(null != validate)
            return new GenericResponse(Const.RESPONSECODE[1],validate, Const.FAILED);

        //building Medication model
        MedicationModel medicationModel = new MedicationModel(request);

        try {
            //saving medication on database
            medicationRespository.save(medicationModel);
            return new GenericResponse(Const.RESPONSECODE[0], "saved", Const.SUCCESSFUL);
        }
        catch (DuplicateKeyException dataEx){
            return new GenericResponse(Const.RESPONSECODE[1], "Duplicate medication code", Const.FAILED);
        }
        catch (Exception ex){
            return new GenericResponse(Const.RESPONSECODE[1], ex.getMessage(), Const.FAILED);
        }

    }

    public List<MedicationModel> findAllMedication(){
        return medicationRespository.findAll();
    }
}
