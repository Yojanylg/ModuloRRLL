package com.example.ModuloBack.controller;

import com.example.ModuloBack.model.Permiso;
import com.example.ModuloBack.services.PermisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/permisos")
public class PermisoController {
    @Autowired
    private PermisoService permisoService;

    @PostMapping("/solicitar")
    public ResponseEntity<Permiso> solicitarPermiso(@RequestBody Permiso permiso) {
        return new ResponseEntity<>(permisoService.solicitarPermiso(permiso), HttpStatus.CREATED);
    }
}
