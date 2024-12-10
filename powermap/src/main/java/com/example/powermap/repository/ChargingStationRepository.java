package com.example.powermap.repository;

import com.example.powermap.model.ChargingStation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ChargingStationRepository extends JpaRepository<ChargingStation, Long> {

    List<ChargingStation> findByAvailable(boolean available);
}
