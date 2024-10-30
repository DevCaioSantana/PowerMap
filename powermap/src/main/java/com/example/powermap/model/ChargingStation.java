package com.example.powermap.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "charging_stations")
@Data
@NoArgsConstructor
public class ChargingStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome da estação é obrigatório.")
    @Size(min = 2, max = 100, message = "O nome da estação deve ter entre 2 e 100 caracteres.")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "A localização é obrigatória.")
    @Size(min = 2, max = 255, message = "A localização deve ter entre 2 e 255 caracteres.")
    @Column(nullable = false)
    private String location;

    @Min(value = 1, message = "O número de vagas deve ser no mínimo 1.")
    @Max(value = 1000, message = "O número de vagas não pode exceder 1000.")
    @Column(nullable = false)
    private int numberOfSlots;

    @Min(value = 1, message = "A capacidade de carregamento deve ser no mínimo 1kW.")
    @Max(value = 10000, message = "A capacidade de carregamento não pode exceder 10000kW.")
    @Column(nullable = false)
    private int capacity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StationStatus status;

    @NotNull(message = "A disponibilidade deve ser informada.")
    @Column(nullable = false)
    private Boolean available;
}
