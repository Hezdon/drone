package com.example.drone.repository;

import com.example.drone.model.DroneModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DroneRepository extends JpaRepository<DroneModel, Long> {
    Optional<DroneModel> findBySerialNumber(String serialNumber);
    List<DroneModel> findByState(String state);
}
