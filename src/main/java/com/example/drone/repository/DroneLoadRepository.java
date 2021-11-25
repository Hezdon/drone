package com.example.drone.repository;

import com.example.drone.model.DroneLoad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneLoadRepository extends JpaRepository<DroneLoad, Long> {
}
