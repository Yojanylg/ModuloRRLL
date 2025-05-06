package com.example.ModuloRH.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "empleado")
public class Empleado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private int idEmpleado;

    @Column
    private String nombre;

    @Column(name = "apellido_1", nullable = true)
    private String apellido_1;

    @Column(name = "apellido_2", nullable = true)
    private String apellido_2;

    @Column
    private String dni;

    @Column
    private String email;

    @Column(nullable = false, length = 100)
    private String upassword;

    @Column
    private String direccion;

    @Column(name = "fecha_alta")
    private Date fecha_alta;

    @Column(name = "fecha_baja")
    private Date fecha_baja;

    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL)
    private List<Permiso> permisos;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_empleado")
    @JsonIgnore
    private List<RegistroJornada> registros;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_jefe")
    private Empleado jefe; //Es el empleado con el rol de admin

    public Empleado(String nombre, String apellido1, String apellido2, String dni, String email, String password, String direccion, Date fechaAlta, Date fechaBaja, Empleado jefe) {
        this.nombre = nombre;
        this.apellido_1 = apellido1;
        this.apellido_2 = apellido2;
        this.dni = dni;
        this.email = email;
        this.upassword = password;
        this.direccion = direccion;
        this.fecha_alta = fechaAlta;
        this.fecha_baja = fechaBaja;
        this.jefe = jefe;
    }

    public Empleado() {
    }

    public Empleado(int idEmpleado, String nombre, String apellido1, String apellido2, String dni, String email, String upassword, String direccion, Date fechaAlta, Date fechaBaja, List<Permiso> permisos, List<RegistroJornada> registros, Empleado jefe) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.apellido_1 = apellido1;
        this.apellido_2 = apellido2;
        this.dni = dni;
        this.email = email;
        this.upassword = upassword;
        this.direccion = direccion;
        this.fecha_alta = fechaAlta;
        this.fecha_baja = fechaBaja;
        this.permisos = permisos;
        this.registros = registros;
        this.jefe = jefe;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido_1;
    }

    public void setApellido1(String apellido_1) {
        this.apellido_1 = apellido_1;
    }

    public String getApellido2() {
        return apellido_2;
    }

    public void setApellido2(String apellido_2) {
        this.apellido_2 = apellido_2;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaAlta() {
        return fecha_alta;
    }

    public void setFechaAlta(Date fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

    public Date getFechaBaja() {
        return fecha_baja;
    }

    public void setFechaBaja(Date fecha_baja) {
        this.fecha_baja = fecha_baja;
    }

    public List<Permiso> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<Permiso> permisos) {
        this.permisos = permisos;
    }

    public List<RegistroJornada> getRegistros() {
        return registros;
    }

    public void setRegistros(List<RegistroJornada> registros) {
        this.registros = registros;
    }

    public Empleado getJefe() {
        return jefe;
    }

    public void setJefe(Empleado jefe) {
        this.jefe = jefe;
    }
}