package com.example.drone.repository;

import com.example.drone.model.MedicationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicationRespository extends JpaRepository<MedicationModel, Long> {

    Optional<MedicationModel> findByCode(String code);

}
