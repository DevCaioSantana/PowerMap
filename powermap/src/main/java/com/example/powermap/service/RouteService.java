package com.example.powermap.service;

import com.example.powermap.model.Route;
import com.example.powermap.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    public Route addRoute(Route route) {
        // Verifica se o usuário está presente
        if (route.getUser() == null || route.getUser().getId() == null) {
            throw new IllegalArgumentException("O usuário associado à rota é obrigatório.");
        }

        // Registra o timestamp somente se não for fornecido
        if (route.getTimestamp() == null)  {
            route.setTimestamp(LocalDateTime.now());
        }

        // Salva a rota no repositório
        return routeRepository.save(route);
    }


    // Adicionar rota aos favoritos (limitar a 3 favoritas)
    public Route addFavorite(Long routeId) {
        Route route = routeRepository.findById(routeId).orElseThrow();

        // Verificar se o usuário já tem 3 rotas favoritas
        List<Route> favorites = routeRepository.findByUserIdAndFavoriteTrue(route.getUser().getId());
        if (favorites.size() >= 3) {
            throw new IllegalStateException("Você só pode ter até 3 rotas favoritas.");
        }

        // Marcar a rota como favorita e salvar
        route.setFavorite(true);
        return routeRepository.save(route);
    }

    // Remover rota dos favoritos
    public Route removeFavorite(Long routeId) {
        Route route = routeRepository.findById(routeId).orElseThrow();
        route.setFavorite(false); // Marcar a rota como não favorita
        return routeRepository.save(route);
    }

    // Buscar rotas favoritas de um usuário
    public List<Route> getFavorites(Long userId) {
        return routeRepository.findByUserIdAndFavoriteTrue(userId);
    }

    // Buscar todas as rotas de um usuário
    public List<Route> getAllRoutes(Long userId) {
        List<Route> routes = routeRepository.findByUserId(userId);
        return routes;
    }

}
