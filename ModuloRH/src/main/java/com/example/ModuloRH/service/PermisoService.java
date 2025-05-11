package com.example.ModuloRH.service;

import com.example.ModuloRH.model.Permiso;

import java.util.List;

public interface PermisoService {
    Permiso crearPermiso(Permiso permiso);
    List<Permiso> obtenerPermisosPorEmpleado(int idEmpleado);
   // Permiso aprobarPermiso (int idPermiso, int idJefe);
   // void rechazarPermiso(int idPermiso, int idJefe);
}
