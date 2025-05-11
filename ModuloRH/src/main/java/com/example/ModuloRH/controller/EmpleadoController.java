package com.example.ModuloRH.controller;

import com.example.ModuloRH.dto.LoginDTO;
import com.example.ModuloRH.dto.SolicitarPermisoDTO;
import com.example.ModuloRH.dto.UsuarioDTO;
import com.example.ModuloRH.model.Empleado;
import com.example.ModuloRH.model.Permiso;
import com.example.ModuloRH.model.RegistroJornada;
import com.example.ModuloRH.model.TipoPermiso;
import com.example.ModuloRH.service.EmpleadoService;
import com.example.ModuloRH.service.TipoPermisoService;
import com.example.ModuloRH.service.TipoPermisoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

@RestController
@RequestMapping("empleado")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;
    @Autowired
    private TipoPermisoService tipoPermisoService;

    @GetMapping("error")
    public String getError(){
        return "Error en la app";
    }

    @GetMapping("one")
    public ResponseEntity<Empleado> getEmpleado(@RequestParam int id){
        return new ResponseEntity<>(empleadoService.getById(id), HttpStatus.OK);
    }

    @PostMapping("usuario")
    public ResponseEntity<Empleado> getUsuario(@RequestBody UsuarioDTO dto){
        return new ResponseEntity<>(empleadoService.getByEmail(dto.email()), HttpStatus.OK);
    }

    @PostMapping("login")
    public ResponseEntity<Boolean> login(@RequestBody LoginDTO dto){

        Empleado empleado = empleadoService.getByEmail(dto.email());

        if (empleado.getUpassword().equals(dto.password())){
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.ok(false);
        }

    }

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

    @PostMapping("salida")
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

    @PostMapping("solicitarPermiso")
    public ResponseEntity<Boolean> solicitarPermiso(@RequestBody SolicitarPermisoDTO dto){

        Empleado empleado = empleadoService.getById(dto.idEmpleado());

        TipoPermiso tipoPermiso = tipoPermisoService.findById(dto.idTipoPermiso());

        Date fechaInicio = dto.fechaInicio();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaInicio);
        calendar.add(Calendar.DAY_OF_MONTH, dto.duracion());

        Date fechaFin = calendar.getTime();

        java.sql.Date fechaInicioSql = new java.sql.Date(fechaInicio.getTime());
        java.sql.Date fechaFinSql = new java.sql.Date(fechaFin.getTime());

        Permiso permiso = new Permiso(tipoPermiso, dto.duracion(), false, fechaInicioSql, fechaFinSql);

        permiso.setEmpleado(empleado);

        empleado.getPermisos().add(permiso);

        empleadoService.actualizarPermisos(empleado);

        return ResponseEntity.ok(true);
    }

    /*
    @PostMapping("/registro")
    public ResponseEntity<Empleado> registrarEmpleado(@RequestBody Empleado empleado) {
        // El jefe se asigna automáticamente si está presente, y se establece el rol de admin si el jefe es proporcionado
        return ResponseEntity.ok(empleadoService.registrarEmpleado(empleado));
    }

     */


}
