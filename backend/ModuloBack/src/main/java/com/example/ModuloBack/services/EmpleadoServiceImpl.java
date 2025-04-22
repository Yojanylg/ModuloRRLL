package com.example.ModuloBack.services;

import com.example.ModuloBack.model.Empleado;
import com.example.ModuloBack.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoServiceImpl implements EmpleadoService{

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public Empleado agregarEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    @Override
    public Empleado modificarEmpleado(Empleado empleado, int id) {
        Empleado existente = empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        existente.setDireccion(empleado.getDireccion());
        existente.setDni(empleado.getDni());
        existente.setNombre(empleado.getNombre());
        existente.setApellido1(empleado.getApellido1());
        existente.setApellido2(empleado.getApellido2());
        existente.setEmail(empleado.getEmail());
        existente.setFechaAlta(empleado.getFechaAlta());
        existente.setFechaBaja(empleado.getFechaBaja());
        return empleadoRepository.save(existente);
    }

    @Override
    public void borrarEmpleado(int id) {
        if (!empleadoRepository.existsById(id)) {
            throw new RuntimeException("Empleado no encontrado");
        }
        empleadoRepository.deleteById(id);
    }

    @Override
    public Empleado buscarEmpleadoPorId(int id) {
        return empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
    }

    @Override
    public boolean validarUsuario(String dni, String email) {
        return empleadoRepository.findByDni(dni)
                .map(empleado -> empleado.getEmail().equals(email))
                .orElse(false);
    }

    @Override
    public List<Empleado> obtenerSubordinados(int idJefe) {
        return empleadoRepository.findByJefe_IdEmpleado(idJefe);
    }
}
