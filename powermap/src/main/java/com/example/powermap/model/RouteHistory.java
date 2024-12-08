package com.example.powermap.model;

import com.example.powermap.model.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "route_history")
@Data
@NoArgsConstructor
public class RouteHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // Usuário associado à rota

    @Column(nullable = false)
    private String startLocation;  // Local de origem

    @Column(nullable = false)
    private String endLocation;    // Local de destino

    @Column(nullable = false)
    private double distance;       // Distância total da rota

    @Column(nullable = false)
    private double duration;       // Duração estimada da rota em minutos

    @Column(nullable = true)
    private boolean favorite;      // Indica se a rota é uma favorita do usuário

    @Column(nullable = false)
    private String timestamp;      // Data e hora em que a rota foi percorrida
}
