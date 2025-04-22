package com.example.ModuloRH.service;

import com.example.ModuloRH.model.Empleado;
import com.example.ModuloRH.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoSermiceImp implements EmpleadoService{

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public Empleado getById(int id) {
        return empleadoRepository.findById(id);
    }

    @Override
    public List<Empleado> getAllEmpleados() {
        return empleadoRepository.findAll();
    }

    public void actualizarRegistroEntrada(Empleado empleado) {
        empleadoRepository.save(empleado);
    }

    @Override
    public void actualizarRegistroSalida(Empleado empleado) {
        empleadoRepository.save(empleado);
    }


}
