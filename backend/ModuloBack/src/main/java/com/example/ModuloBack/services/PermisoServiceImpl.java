package com.example.ModuloBack.services;

import com.example.ModuloBack.model.Permiso;
import com.example.ModuloBack.repository.PermisoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermisoServiceImpl implements PermisoService{

    @Autowired
    private PermisoRepository permisoRepository;

    @Override
    public Permiso solicitarPermiso(Permiso permiso) {
        permiso.setAprobado(false); // Por defecto, los permisos no están aprobados
        return permisoRepository.save(permiso);
    }
}
