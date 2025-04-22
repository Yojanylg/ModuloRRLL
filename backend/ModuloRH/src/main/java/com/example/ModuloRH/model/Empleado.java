package com.example.ModuloRH.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "empleado")
public class Empleado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int idEmpleado;

    @Column
    private String nombre;

    @Column(name = "apellido_1")
    private String apellido1;

    @Column(name = "apellido_2")
    private String apellido2;

    @Column
    private String dni;

    @Column
    private String email;

    @Column
    private String upassword;

    @Column
    private String direccion;

    @Column(name = "fecha_alta")
    private Date fechaAlta;

    @Column(name = "fecha_baja")
    private Date fechaBaja;

    @OneToMany
    @JoinColumn(name = "id_empleado")
    private List<Permiso> permisos;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_empleado")
    private List<RegistroJornada> registros;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_jefe")
    private Empleado jefe;

    public Empleado(String nombre, String apellido1, String apellido2, String dni, String email, String password, String direccion, Date fechaAlta, Date fechaBaja, Empleado jefe) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.dni = dni;
        this.email = email;
        this.upassword = password;
        this.direccion = direccion;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
        this.jefe = jefe;
    }

    public Empleado() {
    }

    public Empleado(int idEmpleado, String nombre, String apellido1, String apellido2, String dni, String email, String upassword, String direccion, Date fechaAlta, Date fechaBaja, List<Permiso> permisos, List<RegistroJornada> registros, Empleado jefe) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.dni = dni;
        this.email = email;
        this.upassword = upassword;
        this.direccion = direccion;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
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
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
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
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
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