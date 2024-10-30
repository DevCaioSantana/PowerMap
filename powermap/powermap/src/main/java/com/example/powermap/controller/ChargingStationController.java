package com.example.powermap.controller;

import com.example.powermap.model.ChargingStation;
import com.example.powermap.model.StationStatus;
import com.example.powermap.service.ChargingStationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stations")
public class ChargingStationController {

    @Autowired
    private ChargingStationService service;

    // Listar todas as estações de carregamento
    @GetMapping
    public List<ChargingStation> getAllStations() {
        return service.findAllStations();
    }

    // Buscar uma estação específica por ID
    @GetMapping("/{id}")
    public ResponseEntity<ChargingStation> getStationById(@PathVariable Long id) {
        Optional<ChargingStation> station = service.findStationById(id);
        return station.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Criar uma nova estação de carregamento
    @PostMapping
    public ResponseEntity<ChargingStation> createStation(@Valid @RequestBody ChargingStation station) {
        ChargingStation savedStation = service.saveStation(station);
        return ResponseEntity.ok(savedStation);
    }

    // Atualizar uma estação de carregamento
    @PutMapping("/{id}")
    public ResponseEntity<ChargingStation> updateStation(@PathVariable Long id, @Valid @RequestBody ChargingStation stationDetails) {
        Optional<ChargingStation> existingStation = service.findStationById(id);
        if (existingStation.isPresent()) {
            ChargingStation station = existingStation.get();
            station.setName(stationDetails.getName());
            station.setLocation(stationDetails.getLocation());
            station.setNumberOfSlots(stationDetails.getNumberOfSlots());
            station.setCapacity(stationDetails.getCapacity());
            station.setStatus(stationDetails.getStatus());
            station.setAvailable(stationDetails.getAvailable());
            return ResponseEntity.ok(service.saveStation(station));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Deletar uma estação de carregamento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStation(@PathVariable Long id) {
        service.deleteStation(id);
        return ResponseEntity.noContent().build();
    }

    // Painel de controle buscar estações por status ("ativo", "inativo", "em manutenção")
    @GetMapping("/status/{status}")
    public ResponseEntity<List<ChargingStation>> getStationsByStatus(@PathVariable StationStatus status) {
        List<ChargingStation> stations = service.findStationsByStatus(status);
        if (stations.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(stations);
    }


    // Buscar estações de carregamento disponíveis (available = true)
    @GetMapping("/available")
    public ResponseEntity<List<ChargingStation>> getAvailableStations() {
        List<ChargingStation> stations = service.findAvailableStations();
        return stations.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(stations);
    }
}
