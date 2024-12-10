package com.example.powermap.repository;

import com.example.powermap.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RouteRepository extends JpaRepository<Route, Long> {

    // Buscar todas as rotas favoritas de um usuário
    List<Route> findByUserIdAndFavoriteTrue(Long userId);

    List<Route> findByUserId(Long userId);
}
