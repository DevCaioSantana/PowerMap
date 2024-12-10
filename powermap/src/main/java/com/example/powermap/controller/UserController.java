/*package com.example.powermap.controller;

import com.example.powermap.model.user.User;
import com.example.powermap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    // Endpoint para registrar um usuário comum (ROLE_USER)
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User newUser = userService.registerUser(user);
        return ResponseEntity.ok(newUser);
    }

    // Endpoint para registrar um administrador (ROLE_ADMIN)    
    @PostMapping("/register-admin")
    public ResponseEntity<User> registerAdmin(@RequestBody User user) {
        User newAdmin = userService.registerAdmin(user);
        return ResponseEntity.ok(newAdmin);
    }

    // Endpoint para autenticação (login)
   /* @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestParam String email, @RequestParam String password) {
        Optional<User> userOpt = userService.authenticate(email, password);
        return userOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(401).build());  // 401 Unauthorized se falhar
    }*/

    // Endpoint para atualizar dados do usuário
  /*  @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User updatedUser) {
        Optional<User> updated = userService.updateUser(userId, updatedUser);
        return updated.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para excluir usuário
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}*/
