package com.example.powermap.model.user;

public record RegisterDTO(String email, String password, String nome, UserRole role) {
}
