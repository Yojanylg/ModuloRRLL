package com.example.ModuloBack.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

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

    @OneToOne(mappedBy = "tipoPermiso", cascade = CascadeType.ALL)
    private Permiso permiso;

    //CONSTRUCTOR SIN ID


    public TipoPermiso(String nombre, String descripcion, int duracion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
    }
}
