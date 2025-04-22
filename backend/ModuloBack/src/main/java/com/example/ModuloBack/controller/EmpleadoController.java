package com.example.ModuloBack.controller;

import com.example.ModuloBack.model.Empleado;
import com.example.ModuloBack.services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @PostMapping("/agregar")
    public ResponseEntity<Empleado> agregarEmpleado(@RequestBody Empleado empleado){
        return new ResponseEntity<>(empleadoService.agregarEmpleado(empleado), HttpStatus.OK);
    }

    @PutMapping("/modificar/{id}")
    public ResponseEntity<Empleado> modificarEmpleado(@RequestBody Empleado empleado, @PathVariable int id) {
        return new ResponseEntity<>(empleadoService.modificarEmpleado(empleado, id), HttpStatus.OK);
    }
    @DeleteMapping("/borrar")
    public ResponseEntity<Void> borrarEmpleado(@PathVariable int id) {
        empleadoService.borrarEmpleado(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Empleado> buscarEmpleadoPorId(@PathVariable int id) {
        return new ResponseEntity<>(empleadoService.buscarEmpleadoPorId(id), HttpStatus.OK);
    }
    @GetMapping("/validar")
    public ResponseEntity<Boolean> validarUsuario(@RequestParam String dni, @RequestParam String email) {
        return new ResponseEntity<>(empleadoService.validarUsuario(dni, email), HttpStatus.OK);
    }
    @GetMapping("/subordinados")
    public ResponseEntity<List<Empleado>> obtenerSubordinados(@PathVariable int idJefe) {
        return new ResponseEntity<>(empleadoService.obtenerSubordinados(idJefe), HttpStatus.OK);
    }

}
