package com.example.ModuloRH.repository;

import com.example.ModuloRH.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
    Empleado findById(int id);
    Empleado findByEmail(String email);

}
