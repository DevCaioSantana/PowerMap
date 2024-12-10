package com.example.powermap.service;

import com.example.powermap.model.ChargingStation;
import com.example.powermap.repository.ChargingStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChargingStationService {

    @Autowired
    private ChargingStationRepository repository;

    public List<ChargingStation> findAllStations() {
        return repository.findAll();
    }

    public ChargingStation saveStation(ChargingStation station) {
        return repository.save(station);
    }

    public void deleteStation(Long id) {
        repository.deleteById(id);
    }

    public List<ChargingStation> findAvailableStations() {
        return repository.findByAvailable(true);
    }
}
