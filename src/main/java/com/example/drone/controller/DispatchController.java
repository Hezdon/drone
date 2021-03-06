package com.example.drone.controller;

import com.example.drone.dto.DroneRequest;
import com.example.drone.dto.GenericResponse;
import com.example.drone.dto.LoadDroneRquest;
import com.example.drone.dto.MedicationRequest;
import com.example.drone.model.DroneModel;
import com.example.drone.model.MedicationModel;
import com.example.drone.service.DispatchService;
import com.example.drone.util.Const;
import com.example.drone.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
public class DispatchController {

    @Autowired
    DispatchService dispatchService;

    @PostMapping("/drone/register")
    public ResponseEntity<?> droneLogRequest(@RequestBody @Valid DroneRequest request){

        GenericResponse response = dispatchService.logDrone(request);
        if(Const.RESPONSECODE[0].equalsIgnoreCase(response.getResponseCode()))
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);

        return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/drone/findall")
    public ResponseEntity<?> findAllDrone(){

        List<DroneModel> response = dispatchService.findAllDrone();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/medication/register")
    public ResponseEntity<?> medicationLogRequest(@RequestBody @Valid MedicationRequest request){

        GenericResponse response = dispatchService.logMedication(request);
        if(Const.RESPONSECODE[0].equalsIgnoreCase(response.getResponseCode()))
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);

        return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping("/medication/findall")
    public ResponseEntity<?> findAllMedication(){

        List<MedicationModel> response = dispatchService.findAllMedication();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/drone/medication/{serialNumber}")
    public ResponseEntity<?> findAllMedicationOnDrone(@PathVariable("serialNumber") String serialNumber){

        GenericResponse response = dispatchService.loadedMedicationOnDrone(serialNumber);
        if(Const.RESPONSECODE[0].equalsIgnoreCase(response.getResponseCode()))
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);

        return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
    }

    @PostMapping("/drone/load")
    public ResponseEntity<?> loadDrone(@RequestBody @Valid LoadDroneRquest request){

        GenericResponse response = dispatchService.loadDrone(request);
        if(Const.RESPONSECODE[0].equalsIgnoreCase(response.getResponseCode()))
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);

        return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping("/drone/idle")
    public ResponseEntity<?> getDronebyState(){

        GenericResponse response = dispatchService.getDroneByState(Const.DRONE_STATE.IDLE.toString());
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/drone/battery/{serialNumber}")
    public ResponseEntity<?> getDroneBatteryLevel(@PathVariable("serialNumber") String serialNumber){

        GenericResponse response = dispatchService.getDroneBatteryLevel(serialNumber);
        if(Const.RESPONSECODE[0].equalsIgnoreCase(response.getResponseCode()))
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);

        return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping("/battery/history")
    public ResponseEntity<?> getDroneBatteryHistory(){

        GenericResponse response = dispatchService.getDroneBatteryHistory();
        if(Const.RESPONSECODE[0].equalsIgnoreCase(response.getResponseCode()))
            return new ResponseEntity<>(response, HttpStatus.OK);

        return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
    }



}
