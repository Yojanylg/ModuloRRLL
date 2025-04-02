package com.example.ModuloBack.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "permisos")
public class Permiso implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int idPermiso;

    @Column
    private int duracion;

    @Column
    private boolean aprobado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;

    @OneToOne
    @JoinColumn(name = "id_permiso")
    private TipoPermiso tipoPermiso;

    //CONSTRUCTOR SIN ID


    public Permiso(int duracion, boolean aprobado, Empleado empleado, TipoPermiso tipoPermiso) {
        this.duracion = duracion;
        this.aprobado = aprobado;
        this.empleado = empleado;
        this.tipoPermiso = tipoPermiso;
    }
}
