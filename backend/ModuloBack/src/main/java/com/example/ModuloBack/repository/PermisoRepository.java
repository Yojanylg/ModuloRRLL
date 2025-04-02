package com.example.ModuloBack.repository;


import com.example.ModuloBack.model.Permiso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermisoRepository extends JpaRepository<Permiso, Integer> {
    List<Permiso> findByEmpleado_IdEmpleado(int idEmpleado);
}
