package com.example.ModuloBack.services;

import com.example.ModuloBack.model.Registro;

public interface RegistroService {
    Registro iniciarJornada(Registro registro);
    Registro detenerJornada(int idRegistro);
}
