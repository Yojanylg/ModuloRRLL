package com.example.ModuloRH.repository;

import com.example.ModuloRH.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
    public Empleado findById(int id);
}
