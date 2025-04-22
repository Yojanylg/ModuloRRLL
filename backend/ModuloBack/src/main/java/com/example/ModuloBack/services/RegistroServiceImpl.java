package com.example.ModuloBack.services;

import com.example.ModuloBack.model.Registro;
import com.example.ModuloBack.repository.RegistroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RegistroServiceImpl implements RegistroService{

    @Autowired
    private RegistroRepository registroRepository;

    @Override
    public Registro iniciarJornada(Registro registro) {
        registro.setFechaInicio(LocalDate.now());
        return registroRepository.save(registro);
    }

    @Override
    public Registro detenerJornada(int idRegistro) {
        Registro registro = registroRepository.findById(idRegistro)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));
        registro.setFechaFin(LocalDate.now());
        return registroRepository.save(registro);
    }
}
