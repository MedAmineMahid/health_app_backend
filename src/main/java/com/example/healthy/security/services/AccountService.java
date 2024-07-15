package com.example.healthy.security.services;

import com.example.healthy.security.entities.Role;
import com.example.healthy.security.entities.User;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    //User createUser(String username, String password, String confirmPassword);
    User createUser(User user);
    Role createRole(String role);
    void addRoleToUser(String username, Role role);

    void removeRoleFromUser(String username, String role);
    User loadUserByUsername(String username);

    String usernameGenerator(String firstName,String lastName);

    String generateRandomPassword();
    //List<String> listRole(User user);

    //void sendPasswordByEmail(String password,String mail);
}
