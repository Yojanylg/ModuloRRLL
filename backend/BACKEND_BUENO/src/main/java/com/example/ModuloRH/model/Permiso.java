package com.example.ModuloRH.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "permisos")
public class Permiso implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_permiso")
    private int idPermiso;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_permiso")
    private TipoPermiso tipoPermiso;

    @Column
    private int duracion;

    @Column
    private boolean aprobado;

    @Column(name = "fecha_inicio")
    private Date fechaInicio;

    @Column(name = "fecha_fin")
    private Date fechaFin;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_empleado")
    @JsonIgnore
    private Empleado empleado;

    public Permiso() {
    }

    public Permiso(int idPermiso, TipoPermiso tipoPermiso, int duracion, boolean aprobado, Date fechaInicio, Date fechaFin) {
        this.idPermiso = idPermiso;
        this.tipoPermiso = tipoPermiso;
        this.duracion = duracion;
        this.aprobado = aprobado;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Permiso(TipoPermiso tipoPermiso, int duracion, boolean aprobado, Date fechaInicio, Date fechaFin) {
        this.tipoPermiso = tipoPermiso;
        this.duracion = duracion;
        this.aprobado = aprobado;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public int getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(int idPermiso) {
        this.idPermiso = idPermiso;
    }

    public TipoPermiso getTipoPermiso() {
        return tipoPermiso;
    }

    public void setTipoPermiso(TipoPermiso tipoPermiso) {
        this.tipoPermiso = tipoPermiso;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public boolean isAprobado() {
        return aprobado;
    }

    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    @Override
    public String toString() {
        return "Permiso{" +
                "idPermiso=" + idPermiso +
                ", tipoPermiso=" + tipoPermiso +
                ", duracion=" + duracion +
                ", aprobado=" + aprobado +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", empleado=" + empleado +
                '}';
    }
}
