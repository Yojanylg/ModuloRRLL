package com.example.ModuloRH.repository;

import com.example.ModuloRH.model.Empleado;
import com.example.ModuloRH.model.Permiso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermisoRepository extends JpaRepository<Permiso, Integer> {
    List<Permiso> findByAprobado(boolean aprobado);
    List<Permiso> findByEmpleado(Empleado empleado);
}
