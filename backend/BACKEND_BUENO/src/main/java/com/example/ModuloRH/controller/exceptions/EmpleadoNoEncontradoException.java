package com.example.ModuloRH.controller.exceptions;

//excepcion personalizada para cuando no existe el empleado por estar dado de baja
public class EmpleadoNoEncontradoException extends RuntimeException{
    public EmpleadoNoEncontradoException(String message) {
        super(message);
    }
}
