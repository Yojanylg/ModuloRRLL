package com.example.ModuloRH.security;

import com.example.ModuloRH.model.Empleado;
import com.example.ModuloRH.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;

@Service
public class CustomerUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Buscar empleado por email
        Empleado empleado = empleadoRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        // Determinar el rol del empleado: si tiene jefe, es admin, si no, es user
        String role = (empleado.getJefe() != null) ? "ADMIN" : "USER";

        // Retornar el usuario con el rol correspondiente
        return User.withUsername(empleado.getEmail())
                .password(empleado.getUpassword())  // Aquí se usa la contraseña encriptada
                .roles(role)  // El rol se asigna dinámicamente
                .build();
    }
}

