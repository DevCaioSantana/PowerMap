package com.example.powermap.controller;

import com.example.powermap.model.ChargingStation;
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

    // Criar uma nova estação de carregamento
    @PostMapping
    public ResponseEntity<ChargingStation> createStation(@Valid @RequestBody ChargingStation station) {
        ChargingStation savedStation = service.saveStation(station);
        return ResponseEntity.ok(savedStation);
    }

    // Deletar uma estação de carregamento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStation(@PathVariable Long id) {
        service.deleteStation(id);
        return ResponseEntity.noContent().build();
    }

    // Buscar estações de carregamento disponíveis (available = true)
    @GetMapping("/available")
    public ResponseEntity<List<ChargingStation>> getAvailableStations() {
        List<ChargingStation> stations = service.findAvailableStations();
        return stations.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(stations);
    }
}
