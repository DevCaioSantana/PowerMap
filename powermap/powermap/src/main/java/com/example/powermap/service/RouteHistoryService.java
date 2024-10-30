package com.example.powermap.service;

import com.example.powermap.model.RouteHistory;
import com.example.powermap.repository.RouteHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RouteHistoryService {

    @Autowired
    private RouteHistoryRepository historyRepository;

    // Adicionar nova rota ao histórico (dados de rota já calculados no frontend)
    public RouteHistory addRouteHistory(RouteHistory routeHistory) {
        routeHistory.setTimestamp(LocalDateTime.now().toString());  // Registra o timestamp da adição

        // Salva a nova rota no histórico
        RouteHistory savedHistory = historyRepository.save(routeHistory);

        // Limitar o histórico às 3 últimas rotas (não favoritas)
        limitRouteHistory(routeHistory.getUser().getId());

        return savedHistory;
    }

    // Limitar o histórico de rotas a 3 últimas (não favoritas)
    private void limitRouteHistory(Long userId) {
        // Buscar todas as rotas não favoritas ordenadas por data (mais recentes primeiro)
        List<RouteHistory> recentHistories = historyRepository.findByUserIdAndFavoriteFalseOrderByTimestampDesc(userId);

        if (recentHistories.size() > 3) {
            // Excluir todas as rotas além das 3 mais recentes
            List<RouteHistory> toDelete = recentHistories.subList(3, recentHistories.size());
            historyRepository.deleteAll(toDelete);
        }
    }

    // Adicionar rota aos favoritos (limitar a 3 favoritas)
    public RouteHistory addFavorite(Long historyId) {
        RouteHistory routeHistory = historyRepository.findById(historyId).orElseThrow();

        // Verificar se o usuário já tem 3 rotas favoritas
        List<RouteHistory> favorites = historyRepository.findByUserIdAndFavoriteTrue(routeHistory.getUser().getId());
        if (favorites.size() >= 3) {
            throw new IllegalStateException("Você só pode ter até 3 rotas favoritas.");
        }

        // Marcar a rota como favorita e salvar
        routeHistory.setFavorite(true);
        return historyRepository.save(routeHistory);
    }

    // Remover rota dos favoritos
    public RouteHistory removeFavorite(Long historyId) {
        RouteHistory routeHistory = historyRepository.findById(historyId).orElseThrow();
        routeHistory.setFavorite(false);  // Marcar a rota como não favorita
        return historyRepository.save(routeHistory);
    }

    // Buscar rotas favoritas de um usuário
    public List<RouteHistory> getFavorites(Long userId) {
        return historyRepository.findByUserIdAndFavoriteTrue(userId);
    }

    // Buscar as últimas 3 rotas (não favoritas) de um usuário
    public List<RouteHistory> getRecentHistory(Long userId) {
        return historyRepository.findByUserIdAndFavoriteFalseOrderByTimestampDesc(userId);
    }
}
