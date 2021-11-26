package com.example.drone.service;

import com.example.drone.dbInterface.DbQueryInf;
import com.example.drone.dto.DroneRequest;
import com.example.drone.dto.GenericResponse;
import com.example.drone.dto.LoadDroneRquest;
import com.example.drone.dto.MedicationRequest;
import com.example.drone.model.BatteryHistory;
import com.example.drone.model.DroneLoad;
import com.example.drone.model.DroneModel;
import com.example.drone.model.MedicationModel;
import com.example.drone.repository.BatteryHistoryRepo;
import com.example.drone.repository.DroneLoadRepository;
import com.example.drone.repository.DroneRepository;
import com.example.drone.repository.MedicationRespository;
import com.example.drone.util.Const;
import com.example.drone.util.Validation;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Optional;

@Service
public class DispatchService {
    @Autowired
    DroneRepository droneRepository;

    @Autowired
    MedicationRespository medicationRespository;
    @Autowired
    DroneLoadRepository droneLoadRepository;
    @Autowired
    DbQueryInf dbQueryInf;
    @Autowired
    BatteryHistoryRepo batteryHistoryRepo;
    ServletContext servletContext;
    DispatchService(ServletContext servletContext){
        this.servletContext = servletContext;
    }
    public GenericResponse logDrone(DroneRequest request){

        //validate Drone properties
        String validate = Validation.validateLogDrone(request);
        if(null != validate)
            return new GenericResponse(Const.RESPONSECODE[1],validate, Const.FAILED);

        //building Drone model
        DroneModel droneModel = new DroneModel(request, Const.DRONE_STATE.IDLE, 0.00f);

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
            byte[] imageByte= Base64.decodeBase64(request.getImage());

            String directory=servletContext.getRealPath("/")+request.getCode()+".jpg";
            new FileOutputStream(directory).write(imageByte);
            medicationModel.setImage(directory);
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

    public GenericResponse loadDrone(LoadDroneRquest request){
        Optional<DroneModel> droneModel = droneRepository.findBySerialNumber(request.getDroneSerialNumber());
        if(!droneModel.isPresent())
            return new GenericResponse(Const.RESPONSECODE[1], "Invalid drone serial number", Const.FAILED);

        Optional<MedicationModel> medicationModel = medicationRespository.findByCode(request.getMedicationCode());
        if(!medicationModel.isPresent())
            return new GenericResponse(Const.RESPONSECODE[1], "Invalid medication code", Const.FAILED);

        DroneModel drone = droneModel.get();
        MedicationModel medication = medicationModel.get();
        if(Const.BATTERY_LOW > drone.getBatteryCapacity())
            return new GenericResponse(Const.RESPONSECODE[1], "Battery level below 25%", Const.FAILED);

        if(!Const.DRONE_STATE.IDLE.toString().equalsIgnoreCase(drone.getState()))
            return new GenericResponse(Const.RESPONSECODE[1], "Drone can not load medication", Const.FAILED);

        if(!Const.MEDICATION_STATE.AVAILABLE.toString().equalsIgnoreCase(medication.getStatus()))
            return new GenericResponse(Const.RESPONSECODE[1], "Medication is not available for loading", Const.FAILED);

        if(medication.getWeight() > drone.getAvailableWeight())
            return new GenericResponse(Const.RESPONSECODE[1], "Drone available weight is lesser", Const.FAILED);

        DroneLoad droneLoad = new DroneLoad(drone, medication);
        droneLoadRepository.save(droneLoad);

        drone.setState(Const.DRONE_STATE.LOADING.toString());
        drone.setAvailableWeight(drone.getAvailableWeight() - medication.getWeight());
        droneRepository.save(drone);

        medicationModel.get().setStatus(Const.MEDICATION_STATE.ONLOAD.toString());
        medicationRespository.save(medicationModel.get());

        return new GenericResponse(Const.RESPONSECODE[0], request.getMedicationCode()+" loaded on "+request.getDroneSerialNumber(), Const.SUCCESSFUL);
    }

    public GenericResponse loadedMedicationOnDrone(String serialNumber){
        Optional<DroneModel> droneModel = droneRepository.findBySerialNumber(serialNumber);
        if(!droneModel.isPresent())
            return new GenericResponse(Const.RESPONSECODE[1], "Invalid drone serial number", Const.FAILED);

        List<MedicationModel> medicationModels = dbQueryInf.findAllMedicationbyDrone(droneModel.get().getId(), Const.ACTIVE);

        return new GenericResponse(Const.RESPONSECODE[0], Const.SUCCESSFUL , Const.SUCCESSFUL, medicationModels);
    }

    public GenericResponse getDroneByState(String state){
        List<DroneModel> droneModel = droneRepository.findByState(state);
        return new GenericResponse(Const.RESPONSECODE[0], Const.SUCCESSFUL , Const.SUCCESSFUL, droneModel);
    }

    public GenericResponse getDroneBatteryLevel(String serialNumber){
        Optional<DroneModel> droneModel = droneRepository.findBySerialNumber(serialNumber);
        if(!droneModel.isPresent())
            return new GenericResponse(Const.RESPONSECODE[1], "Invalid drone serial number", Const.FAILED);

        return new GenericResponse(Const.RESPONSECODE[0], Const.SUCCESSFUL , Const.SUCCESSFUL, droneModel.get().getBatteryCapacity());
    }

    public void batteryCheck(DroneModel droneModel){
        droneRepository.save(droneModel);
        BatteryHistory batteryHistory = new BatteryHistory(droneModel);
        batteryHistoryRepo.save(batteryHistory);
        if(Const.DRONE_STATE.DELIVERED.toString().equalsIgnoreCase(droneModel.getState())){
            dbQueryInf.updateMedicationAndDroneLoadStatus(droneModel.getId());

        }
    }

    public GenericResponse getDroneBatteryHistory(){
        List<BatteryHistory> batteryHistory = batteryHistoryRepo.findAll();

        return new GenericResponse(Const.RESPONSECODE[0], Const.SUCCESSFUL , Const.SUCCESSFUL, batteryHistory);
    }

}
