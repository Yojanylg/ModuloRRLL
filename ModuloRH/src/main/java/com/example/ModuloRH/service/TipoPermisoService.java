package com.example.ModuloRH.service;

import com.example.ModuloRH.model.TipoPermiso;

import java.util.List;

public interface TipoPermisoService {

    List<TipoPermiso> tiposPermiso();
    TipoPermiso findById(int id);
}
