package com.example.drone.controller;

import com.example.drone.dto.DroneRequest;
import com.example.drone.dto.GenericResponse;
import com.example.drone.model.DroneModel;
import com.example.drone.service.DispatchService;
import com.example.drone.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController("/api/v1/dispatch")
public class DispatchController {

    @Autowired
    DispatchService dispatchService;

    @PostMapping("/outBound/statusCheck/v1")
    public ResponseEntity<?> droneLogRequest(@RequestBody @Valid DroneRequest request){

        GenericResponse response = dispatchService.logDrone(request);
        if(Const.RESPONSECODE[0].equalsIgnoreCase(response.getResponseCode()))
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);

        return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
    }

}
