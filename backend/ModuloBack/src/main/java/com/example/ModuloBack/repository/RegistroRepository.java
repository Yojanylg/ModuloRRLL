package com.example.ModuloBack.repository;


import com.example.ModuloBack.model.Registro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistroRepository extends JpaRepository<Registro, Integer> {
    List<Registro> findByEmpleado_IdEmpleado(int idEmpleado);
}
