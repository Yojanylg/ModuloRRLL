package com.example.ModuloBack.services;

import com.example.ModuloBack.model.Empleado;

import java.util.List;

public interface EmpleadoService {
    Empleado agregarEmpleado(Empleado empleado);
    Empleado modificarEmpleado(Empleado empleado, int id);
    void borrarEmpleado(int id);
    Empleado buscarEmpleadoPorId(int id);
    boolean validarUsuario(String dni, String email);
    List<Empleado> obtenerSubordinados(int idJefe);
}
