package com.example.powermap.controller;

import com.example.powermap.mapper.RouteMapper;
import com.example.powermap.model.DTO.RouteDTO;
import com.example.powermap.model.Route;
import com.example.powermap.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/routes")
public class RouteController {
    private final RouteService routeService;
    private final RouteMapper routeMapper;
    // Adicionar uma nova rota
    @PostMapping
    public ResponseEntity<Route> addRoute(@RequestBody Route route) {
        try {
            // Verifica se a rota contém um usuário associado
            if (route.getUser() == null || route.getUser().getId() == null) {
                return ResponseEntity.badRequest().build(); // Retorna erro 400 se o usuário não for fornecido
            }

            // Salva a rota usando o serviço
            Route savedRoute = routeService.addRoute(route);

            // Retorna a rota salva
            return ResponseEntity.status(HttpStatus.CREATED).body(savedRoute);
        } catch (Exception e) {
            System.err.println("Erro ao adicionar a rota: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    // Buscar todas as rotas de um usuário
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RouteDTO>> getAllRoutes(@PathVariable Long userId) {
        List<Route> routes = routeService.getAllRoutes(userId);
        return ResponseEntity.ok(routeMapper.toRouteDTO(routes));
    }

    // Adicionar rota aos favoritos
    @PostMapping("/{routeId}/favorite")
    public ResponseEntity<Route> addFavorite(@PathVariable Long routeId) {
        Route updatedRoute = routeService.addFavorite(routeId);
        return ResponseEntity.ok(updatedRoute);
    }

    // Remover rota dos favoritos
    @DeleteMapping("/{routeId}/favorite")
    public ResponseEntity<Route> removeFavorite(@PathVariable Long routeId) {
        Route updatedRoute = routeService.removeFavorite(routeId);
        return ResponseEntity.ok(updatedRoute);
    }

    // Buscar todas as rotas favoritas de um usuário
    @GetMapping("/user/{userId}/favorites")
    public ResponseEntity<List<Route>> getFavorites(@PathVariable Long userId) {
        List<Route> favorites = routeService.getFavorites(userId);
        return ResponseEntity.ok(favorites);
    }
}
