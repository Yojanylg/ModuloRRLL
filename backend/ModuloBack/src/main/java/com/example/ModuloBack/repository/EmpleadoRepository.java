package com.example.ModuloBack.repository;

import com.example.ModuloBack.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
    Optional<Empleado> findByDni(String dni);
    Optional<Empleado> findByEmail(String email); // Buscar empleado por email
    List<Empleado> findByJefe_IdEmpleado(int idJefe);
}
