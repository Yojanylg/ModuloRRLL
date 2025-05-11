package com.example.ModuloRH.repository;

import com.example.ModuloRH.model.TipoPermiso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TipoPermisoRepository extends JpaRepository<TipoPermiso, Integer> {

    TipoPermiso findById(int id);

}
