package com.example.ModuloBack.services;

import com.example.ModuloBack.model.Empleado;

public interface AuthService {
    String registerEmpleado(Empleado empleado);

    String loginEmpleado(String email, String password);
}
