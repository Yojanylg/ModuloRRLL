package com.example.ModuloRH.controller;

import com.example.ModuloRH.model.Empleado;
import com.example.ModuloRH.model.RegistroJornada;
import com.example.ModuloRH.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("empleado")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping("error")
    public String getError(){
        return "Error en la app";
    }

    // Devuelve los datos de un empleado para mostrarlos en el front

    @GetMapping("one")
    public ResponseEntity<Empleado> getEmpleado(@RequestParam int id){
        return new ResponseEntity<>(empleadoService.getById(id), HttpStatus.OK);
    }

    // devuelve todos los datos, simplemente para prueba

    @GetMapping("all")
    public ResponseEntity<List<Empleado>> getEmpleados(){
        return new ResponseEntity<>(empleadoService.getAllEmpleados(), HttpStatus.OK);
    }

    // permite a un usuario registrar su entrada

    @PostMapping("entrada")
    public ResponseEntity<?> registrarEntrada(@RequestParam int id){
        Empleado empleado = empleadoService.getById(id);
        if (empleado == null) {
            return ResponseEntity.notFound().build();
        }

        RegistroJornada nuevo = new RegistroJornada(LocalDateTime.now(), null);

        empleado.getRegistros().add(nuevo);

        empleadoService.actualizarRegistroEntrada(empleado);

        return ResponseEntity.ok(empleado);
    }

    // permite a un usuario registrar su salida

    @PutMapping("salida")
    public ResponseEntity<?> registrarSalida(@RequestParam int id) {
        Empleado empleado = empleadoService.getById(id);
        if (empleado == null) {
            return ResponseEntity.notFound().build();
        }


        // Buscar el último registro sin salida

        RegistroJornada ultimoRegistro = empleado.getRegistros().stream()
                .filter(r -> r.getFechaFin() == null)
                .max(Comparator.comparing(RegistroJornada::getFechaInicio))
                .orElse(null);

        if (ultimoRegistro == null) {
            return ResponseEntity.badRequest().body("No hay registro de entrada pendiente de salida.");
        }

        ultimoRegistro.setFechaFin(LocalDateTime.now());

        empleadoService.actualizarRegistroSalida(empleado);

        return ResponseEntity.ok(ultimoRegistro);
    }

}
