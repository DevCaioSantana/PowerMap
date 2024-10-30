package com.example.powermap.repository;

import com.example.powermap.model.RouteHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RouteHistoryRepository extends JpaRepository<RouteHistory, Long> {

    // Buscar todas as rotas não favoritas de um usuário, ordenadas por data (mais recentes primeiro)
    List<RouteHistory> findByUserIdAndFavoriteFalseOrderByTimestampDesc(Long userId);

    // Buscar todas as rotas favoritas de um usuário
    List<RouteHistory> findByUserIdAndFavoriteTrue(Long userId);
}
