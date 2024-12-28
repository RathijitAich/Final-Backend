package net.zeonsoftwares.fitness.controller;

import net.zeonsoftwares.fitness.entity.AdminEntity;
import net.zeonsoftwares.fitness.entity.TrainerEntity;
import net.zeonsoftwares.fitness.entity.UserEntity;
import net.zeonsoftwares.fitness.repository.AdminRepository;
import net.zeonsoftwares.fitness.repository.TrainerRepository;
import net.zeonsoftwares.fitness.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        // Extract data from the request body
        String user_id = loginData.get("user_id");
        String password = loginData.get("password");
        String role = loginData.get("role");

        // Validate input fields
        if (user_id == null || password == null || role == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "message", "Missing required fields: username, password, and role",
                    "status", "error"));
        }

        // Log the login attempt
        // System.out.println("Login attempt: Username=" + username + ", Role=" + role);

        // Handle login logic based on role
        switch (role.toLowerCase()) {
            case "admin":
                // Find admin by adminId
                AdminEntity admin = adminRepository.findById(user_id).orElse(null);
                if (admin != null && admin.getAdminPassword().equals(password)) {
                    return ResponseEntity.ok(Map.of(
                            "message", "Login Successful",
                            "role", "admin"));
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                            "message", "Invalid admin credentials",
                            "status", "error"));
                }

            case "trainer":
                // Find trainer by trainerId
                TrainerEntity trainer = trainerRepository.findById(user_id).orElse(null);
                if (trainer != null && trainer.getTrainerPassword().equals(password)) {
                    return ResponseEntity.ok(Map.of(
                            "message", "Login Successful",
                            "role", "trainer"));
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                            "message", "Invalid trainer credentials",
                            "status", "error"));
                }
            case "user":
                // Find user by userId
                UserEntity user = userRepository.findById(user_id).orElse(null);
                if (user != null && user.getPassword().equals(password)) {
                    return ResponseEntity.ok(Map.of(
                            "message", "Login Successful",
                            "role", "user"));
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                            "message", "Invalid user credentials",
                            "status", "error"));
                }


            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                        "message", "Invalid role. Accepted roles: admin, trainer,user",
                        "status", "error"));
        }
    }
}
