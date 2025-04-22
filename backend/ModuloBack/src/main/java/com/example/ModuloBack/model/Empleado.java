package com.example.ModuloBack.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

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

    @OneToMany(mappedBy = "empleado", fetch = FetchType.EAGER)
    private List<Permiso> permisos;

    @OneToMany(mappedBy = "empleado", fetch = FetchType.EAGER)
    private List<Registro> registros;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_jefe")
    private Empleado jefe;

   // @OneToMany(mappedBy = "jefe", fetch = FetchType.EAGER)
   // private List<Empleado> subordinados;

    //CONSTRUCTOR SIN ID

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
}


