package com.example.ModuloBack.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;

    @OneToOne
    @JoinColumn(name = "id_permiso")
    private TipoPermiso tipoPermiso;

    @Column
    private int duracion;

    @Column
    private boolean aprobado;

    @Column(name = "fecha_inicio")
    private Date fechaInicio;

    @Column(name = "fecha_fin")
    private Date fechaFin;

    //CONSTRUCTOR SIN ID

    public Permiso(Empleado empleado, TipoPermiso tipoPermiso, int duracion, Date fechaInicio, Date fechaFin) {
        this.empleado = empleado;
        this.tipoPermiso = tipoPermiso;
        this.duracion = duracion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }
}
