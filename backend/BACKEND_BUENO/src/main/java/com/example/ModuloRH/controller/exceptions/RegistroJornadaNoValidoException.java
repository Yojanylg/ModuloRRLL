package com.example.ModuloRH.controller.exceptions;

//excepcion para cuando el empleado ya tiene registrada una jornada sin salida
public class RegistroJornadaNoValidoException extends RuntimeException{
    public RegistroJornadaNoValidoException(String message) {
        super(message);
    }
}
