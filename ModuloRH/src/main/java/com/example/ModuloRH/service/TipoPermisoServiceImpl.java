package com.example.ModuloRH.service;

import com.example.ModuloRH.model.TipoPermiso;
import com.example.ModuloRH.repository.TipoPermisoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoPermisoServiceImpl implements TipoPermisoService{

    @Autowired
    private TipoPermisoRepository tipoPermisoRepository;


    @Override
    public List<TipoPermiso> tiposPermiso() {
        return tipoPermisoRepository.findAll();
    }

    @Override
    public TipoPermiso findById(int id) {
        return tipoPermisoRepository.findById(id);
    }


}
