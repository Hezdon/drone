package com.example.drone.controller;

import com.example.drone.dto.DroneRequest;
import com.example.drone.dto.GenericResponse;
import com.example.drone.dto.MedicationRequest;
import com.example.drone.model.DroneModel;
import com.example.drone.model.MedicationModel;
import com.example.drone.service.DispatchService;
import com.example.drone.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController("/api/v1")
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

    @GetMapping("/drone/findall")
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

}
