package com.example.ModuloRH.controller;

import com.example.ModuloRH.controller.exceptions.EmpleadoNoEncontradoException;
import com.example.ModuloRH.controller.exceptions.RegistroJornadaNoValidoException;
import com.example.ModuloRH.model.Empleado;
import com.example.ModuloRH.model.LoginRequest;
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

    @CrossOrigin(origins = "*")
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
    public ResponseEntity<?> registrarEntrada(@RequestParam int id) {
        try {
            Empleado empleado = empleadoService.getById(id);
            empleadoService.actualizarRegistroEntrada(empleado);
            return ResponseEntity.ok(empleado);
        } catch (EmpleadoNoEncontradoException | RegistroJornadaNoValidoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // permite a un usuario registrar su salida

    @PutMapping("salida")
    public ResponseEntity<?> registrarSalida(@RequestParam int id) {
        try {
            Empleado empleado = empleadoService.getById(id);
            empleadoService.actualizarRegistroSalida(empleado);
            return ResponseEntity.ok(empleado);
        } catch (EmpleadoNoEncontradoException | RegistroJornadaNoValidoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //Seguridad

    // Registro de nuevo empleado
    @PostMapping("/registro")
    public ResponseEntity<Empleado> registrarEmpleado(@RequestBody Empleado empleado) {
        // El jefe se asigna automáticamente si está presente, y se establece el rol de admin si el jefe es proporcionado
        return ResponseEntity.ok(empleadoService.registrarEmpleado(empleado));
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        try {
            String token = empleadoService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
            return ResponseEntity.ok(token);  // Retorna el token de autenticación
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error de autenticación: " + e.getMessage());  // Devolver el mensaje de error
        }
    }

    // Cambio de contraseña
    @PutMapping("/cambiar-contraseña")
    public ResponseEntity<String> cambiarContraseña(@RequestParam String email, @RequestParam String nuevaContraseña) {
        empleadoService.cambiarContraseña(email, nuevaContraseña);
        return ResponseEntity.ok("Contraseña cambiada con éxito");
    }

}
