package com.example.powermap.controller;

import com.example.powermap.model.RouteHistory;
import com.example.powermap.service.RouteHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routes/history")
public class RouteHistoryController {

    @Autowired
    private RouteHistoryService routeHistoryService;

    // Endpoint para criar uma nova rota no histórico
    @PostMapping
    public ResponseEntity<RouteHistory> createRouteHistory(@RequestBody RouteHistory routeHistory) {
        RouteHistory createdRouteHistory = routeHistoryService.addRouteHistory(routeHistory);
        return ResponseEntity.ok(createdRouteHistory);
    }

    // Endpoint para buscar as últimas 3 rotas não favoritas de um usuário
    @GetMapping("/user/{userId}/recent")
    public ResponseEntity<List<RouteHistory>> getRecentHistory(@PathVariable Long userId) {
        List<RouteHistory> recentHistories = routeHistoryService.getRecentHistory(userId);
        return recentHistories.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(recentHistories);
    }

    // Endpoint para adicionar uma rota aos favoritos
    @PostMapping("/{routeHistoryId}/favorite")
    public ResponseEntity<RouteHistory> addFavorite(@PathVariable Long routeHistoryId) {
        RouteHistory favoriteRoute = routeHistoryService.addFavorite(routeHistoryId);
        return ResponseEntity.ok(favoriteRoute);
    }

    // Endpoint para remover uma rota dos favoritos
    @DeleteMapping("/{routeHistoryId}/favorite")
    public ResponseEntity<RouteHistory> removeFavorite(@PathVariable Long routeHistoryId) {
        RouteHistory routeHistory = routeHistoryService.removeFavorite(routeHistoryId);
        return ResponseEntity.ok(routeHistory);
    }

    // Endpoint para buscar todas as rotas favoritas de um usuário
    @GetMapping("/user/{userId}/favorites")
    public ResponseEntity<List<RouteHistory>> getFavoriteRoutes(@PathVariable Long userId) {
        List<RouteHistory> favoriteRoutes = routeHistoryService.getFavorites(userId);
        return favoriteRoutes.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(favoriteRoutes);
    }
}
