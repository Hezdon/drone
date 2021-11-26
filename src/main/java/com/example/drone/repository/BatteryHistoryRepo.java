package com.example.drone.repository;

import com.example.drone.model.BatteryHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatteryHistoryRepo extends JpaRepository<BatteryHistory, Long> {
}
