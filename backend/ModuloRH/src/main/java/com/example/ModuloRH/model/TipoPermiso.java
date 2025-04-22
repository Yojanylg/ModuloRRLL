package com.example.ModuloRH.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "tipo_permiso")
public class TipoPermiso implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int idTipo;

    @Column
    private String nombre;

    @Column
    private String descripcion;

    @Column
    private int duracion;

    public TipoPermiso() {
    }

    public TipoPermiso(int idTipo, String nombre, String descripcion, int duracion) {
        this.idTipo = idTipo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
    }

    public TipoPermiso(String nombre, String descripcion, int duracion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
    }
}
