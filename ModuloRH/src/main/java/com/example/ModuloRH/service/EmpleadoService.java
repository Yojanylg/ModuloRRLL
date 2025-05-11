package com.example.ModuloRH.service;

import com.example.ModuloRH.model.Empleado;

import java.util.List;

public interface EmpleadoService {

    Empleado getById(int id);
    List<Empleado> getAllEmpleados();
    void actualizarRegistroEntrada(Empleado empleado);
    void actualizarPermisos(Empleado empleado);
    void actualizarRegistroSalida(Empleado empleado);
    Empleado getByEmail(String email);
}
