package com.example.powermap.model;

import com.example.powermap.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "routes") // Alterado o nome da tabela para "routes"
@Data
@NoArgsConstructor
public class Route implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    @Column(nullable = false)
    private String startLocation;  // Local de origem (nome descritivo)

    @Column(nullable = false)
    private String endLocation;    // Local de destino (nome descritivo)

    @Column(nullable = false)
    private double startLatitude;  // Latitude de origem

    @Column(nullable = false)
    private double startLongitude; // Longitude de origem

    @Column(nullable = false)
    private double endLatitude;    // Latitude de destino

    @Column(nullable = false)
    private double endLongitude;   // Longitude de destino

    @Column(nullable = false)
    private double distance;       // Distância total da rota

    @Column(nullable = false)
    private double duration;       // Duração estimada da rota em minutos

    @Column(nullable = true)
    private boolean favorite;      // Indica se a rota é uma favorita do usuário

    @Column(nullable = true)
    private LocalDateTime timestamp;      // Data e hora em que a rota foi percorrida
}
