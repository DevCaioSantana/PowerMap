package com.example.powermap.model.DTO;

import com.example.powermap.model.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
public class RouteDTO {

    private Long id;
    private UserDTO user;
    private String startLocation;  // Local de origem (nome descritivo)
    private String endLocation;    // Local de destino (nome descritivo)
    private double startLatitude;  // Latitude de origem
    private double startLongitude; // Longitude de origem
    private double endLatitude;    // Latitude de destino
    private double endLongitude;   // Longitude de destino
    private double distance;       // Distância total da rota
    private double duration;       // Duração estimada da rota em minutos
    private boolean favorite;      // Indica se a rota é uma favorita do usuário
    private LocalDateTime timestamp;      // Data e hora em que a rota foi percorrida
}
