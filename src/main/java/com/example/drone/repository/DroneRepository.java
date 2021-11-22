package com.example.drone.repository;

import com.example.drone.model.DroneModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneRepository extends JpaRepository<DroneModel, Long> {
}
