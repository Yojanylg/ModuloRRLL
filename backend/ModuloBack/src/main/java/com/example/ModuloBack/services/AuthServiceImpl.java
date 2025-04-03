package com.example.ModuloBack.services;

import com.example.ModuloBack.model.Empleado;
import com.example.ModuloBack.repository.EmpleadoRepository;
import com.example.ModuloBack.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public String registerEmpleado(Empleado empleado) {
        // Verificar si el empleado ya está en la base de datos (usando email)
        Optional<Empleado> existingEmpleado = empleadoRepository.findByEmail(empleado.getEmail());

        // Si el empleado existe, solo generamos el token, no lo registramos de nuevo
        if (existingEmpleado.isPresent()) {
            // Generamos el token para el empleado existente
            return jwtUtil.generateToken(existingEmpleado.get().getEmail());
        }

        // Si el empleado no está registrado, retornamos un mensaje
        return "El empleado no está registrado";
    }

    @Override
    public String loginEmpleado(String email, String password) {
        Optional<Empleado> empleado = empleadoRepository.findByEmail(email);
        if (empleado.isEmpty()) {
            return "Empleado no encontrado";
        }

        // Comparar la contraseña encriptada con la proporcionada
        if (!passwordEncoder.matches(password, empleado.get().getPassword())) {
            return "Contraseña incorrecta";
        }

        // Generar un token JWT si las credenciales son correctas
        return jwtUtil.generateToken(empleado.get().getEmail());
    }
}
