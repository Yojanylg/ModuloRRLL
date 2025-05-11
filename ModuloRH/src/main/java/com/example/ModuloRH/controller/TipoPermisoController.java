package com.example.ModuloRH.controller;

import com.example.ModuloRH.model.TipoPermiso;
import com.example.ModuloRH.service.TipoPermisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("tipoPermiso")
public class TipoPermisoController {

    @Autowired
    private TipoPermisoService tipoPermisoService;


    @GetMapping("all")
    public ResponseEntity<List<TipoPermiso>> getAll(){
        return new ResponseEntity<>(tipoPermisoService.tiposPermiso(), HttpStatus.OK);
    }

}
