package com.example.ModuloBack.controller;

import com.example.ModuloBack.model.Empleado;
import com.example.ModuloBack.security.JwtUtil;
import com.example.ModuloBack.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Endpoint para registrar o "logear" a un empleado (solo si ya existe)
    @PostMapping("/register")  // Podrías cambiar a "/login" si prefieres
    public ResponseEntity<?> registerEmpleado(@RequestBody Empleado empleado) {
        String response = authService.registerEmpleado(empleado);  // Llama a registerEmpleado para login
        if (response.equals("El empleado no está registrado")) {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok().body("Empleado registrado con éxito. Token: " + response);
    }

    // Endpoint para iniciar sesión
    @PostMapping("/login")
    public ResponseEntity<?> loginEmpleado(@RequestParam String email, @RequestParam String password) {
        String response = authService.loginEmpleado(email, password);
        if (response.equals("Empleado no encontrado") || response.equals("Contraseña incorrecta")) {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok().body("Token: " + response);
    }
}
