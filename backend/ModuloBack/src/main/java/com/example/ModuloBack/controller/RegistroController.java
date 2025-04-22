package com.example.ModuloBack.controller;

import com.example.ModuloBack.model.Registro;
import com.example.ModuloBack.services.RegistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registros")
public class RegistroController {
    @Autowired
    private RegistroService registroService;

    @PostMapping("/iniciar")
    public ResponseEntity<Registro> iniciarJornada(@RequestBody Registro registro) {
        return new ResponseEntity<>(registroService.iniciarJornada(registro), HttpStatus.CREATED);
    }

    @PutMapping("/detener")
    public ResponseEntity<Registro> detenerJornada(@PathVariable int idRegistro) {
        return new ResponseEntity<>(registroService.detenerJornada(idRegistro), HttpStatus.OK);
    }
}
