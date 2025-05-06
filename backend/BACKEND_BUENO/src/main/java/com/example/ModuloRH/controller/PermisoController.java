package com.example.ModuloRH.controller;

import com.example.ModuloRH.model.Permiso;
import com.example.ModuloRH.service.PermisoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("permisos")
public class PermisoController {

    @Autowired
    private PermisoService permisoService;

    @PostMapping("crear")

    public ResponseEntity<Permiso> crearPermiso(@RequestBody Permiso permiso){
        Permiso nuevo = permisoService.crearPermiso(permiso);
        return ResponseEntity.ok(nuevo);
    }

    @GetMapping("empleado/{id}")
    public ResponseEntity<List<Permiso>>obtenerPermisosPorEmpleado(@PathVariable int id){
        return ResponseEntity.ok(permisoService.obtenerPermisosPorEmpleado(id));
    }

    @PutMapping("aprobar/{id}")
    public ResponseEntity<String> aprobarPermiso(@PathVariable int id, @RequestParam int idJefe) {
        try {
            return ResponseEntity.ok(permisoService.aprobarPermiso(id, idJefe).toString());
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @DeleteMapping("rechazar/{id}")
    public ResponseEntity<String> rechazarPermiso(@PathVariable int id, @RequestParam int idJefe) {
        try {
            permisoService.rechazarPermiso(id, idJefe);
            return ResponseEntity.noContent().build();
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
}
