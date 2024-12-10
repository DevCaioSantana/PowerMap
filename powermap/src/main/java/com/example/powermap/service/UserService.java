/*package com.example.powermap.service;

import com.example.powermap.model.user.User;
import com.example.powermap.model.user.UserRole;
import com.example.powermap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    public User registerAdmin(User user) {
        user.setRole(UserRole.ADMIN); // Define o papel como ADMIN
        return userRepository.save(user);  // Salva o usuário no banco de dados
    }

    public User registerUser(User user) {
        user.setRole(UserRole.USER); // Define o papel como USER
        return userRepository.save(user);
    }

    // Autenticar usuário pelo email e senha
/*   public Optional<User> authenticate(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);
//        if (userOpt.isPresent() && passwordEncoder.matches(password, userOpt.get().getPassword())) {
//            return userOpt;
//        }
        return Optional.empty();  // Retorna vazio se a autenticação falhar
    }*/

    // Atualizar dados do usuário
 /*   public Optional<User> updateUser(Long userId, User updatedUser) {
        return userRepository.findById(userId).map(user -> {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
//            if (!updatedUser.getPassword().isBlank()) {
//                user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
//            }
            return userRepository.save(user);
        });
    }

    // Excluir usuário
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}*/
