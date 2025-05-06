package com.example.ModuloRH.service;

import com.example.ModuloRH.controller.exceptions.EmpleadoNoEncontradoException;
import com.example.ModuloRH.controller.exceptions.RegistroJornadaNoValidoException;
import com.example.ModuloRH.model.Empleado;
import com.example.ModuloRH.model.RegistroJornada;
import com.example.ModuloRH.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class EmpleadoSermiceImp implements EmpleadoService{

    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Empleado getById(int id) {
        Empleado empleado = empleadoRepository.findByIdEmpleado(id);  // Ahora devuelve un Empleado directamente
        if (empleado == null) {
            throw new EmpleadoNoEncontradoException("Empleado no encontrado con id: " + id);  // Lanza excepción si no lo encuentra
        }
        return empleado;
    }

    @Override
    public List<Empleado> getAllEmpleados() {
        return empleadoRepository.findAll();
    }

    public void actualizarRegistroEntrada(Empleado empleado) {

        //Verificar si el empleado esta dado de baja
        if(empleado.getFechaBaja() != null && empleado.getFechaBaja().before(new Date())){
            throw new EmpleadoNoEncontradoException("Este empleado está dado de baja");
        }

        //verificar si ya tiene una entrada registrada

        boolean entradaExistente = empleado.getRegistros().stream()
                .anyMatch(registro -> registro.getFechaFin() == null);//esto es por si ya hay un registro pero sin salida

        if(entradaExistente){
            throw  new RegistroJornadaNoValidoException("Este empleado ya ha registrado una entrada sin salida");
        }
        empleadoRepository.save(empleado);
    }

    @Override
    public void actualizarRegistroSalida(Empleado empleado) {
        // Verificar si el empleado está dado de baja
        if (empleado.getFechaBaja() != null && empleado.getFechaBaja().before(new Date())) {
            throw new EmpleadoNoEncontradoException("Este empleado está dado de baja.");
        }

        // Buscar el último registro de jornada sin salida
        RegistroJornada ultimoRegistro = empleado.getRegistros().stream()
                .filter(r -> r.getFechaFin() == null)
                .max(Comparator.comparing(RegistroJornada::getFechaInicio))
                .orElseThrow(() -> new RegistroJornadaNoValidoException("No hay registro de entrada pendiente de salida."));

        ultimoRegistro.setFechaFin(LocalDateTime.now());
        empleadoRepository.save(empleado);
    }

    //Seguridad

    @Override
    public Empleado registrarEmpleado(Empleado empleado) {
        // Encriptar la contraseña antes de guardar
        empleado.setUpassword(passwordEncoder.encode(empleado.getUpassword()));
        // Asignar rol de admin si tiene jefe (porque el jefe es el admin)
        if (empleado.getJefe() != null) {
            // Si el empleado tiene jefe, entonces se considera que es un admin
            // Si quieres un sistema más flexible para asignar roles, puedes agregar un campo de rol aquí
        }
        return empleadoRepository.save(empleado);
    }

    @Override
    public String authenticate(String email, String password) {
        Empleado empleado = empleadoRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        if (passwordEncoder.matches(password, empleado.getUpassword())) {
            return "Usuario autenticado correctamente";  // Si no usas JWT, podrías usar sesiones o algo similar.
        } else {
            throw new RuntimeException("Credenciales inválidas");
        }
    }

    @Override
    public void cambiarContraseña(String email, String nuevaContraseña) {
        Empleado empleado = empleadoRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        empleado.setUpassword(passwordEncoder.encode(nuevaContraseña));
        empleadoRepository.save(empleado);
    }

}
