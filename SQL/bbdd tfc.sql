CREATE DATABASE tfc;

USE tfc;

create table empleado (
	id int auto_increment,
    nombre varchar(50) NOT NULL,
    apellido_1 varchar(50) NOT NULL,
    apellido_2 varchar(50),
    dni varchar(9) UNIQUE NOT NULL,
    email varchar(50) UNIQUE NOT NULL,
	upassword varchar(50) NOT NULL,
    direccion varchar(100),
    fecha_alta Date NOT NULL,
    fecha_baja Date,
    id_jefe int,
    primary key (id),
    INDEX(id),
    INDEX(dni),
    INDEX(email),
    FOREIGN KEY (id_jefe) REFERENCES empleado(id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE tipo_permiso (
  id INT auto_increment,
  nombre VARCHAR(50) NOT NULL UNIQUE,
  descripcion VARCHAR(100),
  duracion INT NOT NULL,
  PRIMARY KEY (id),
  INDEX (nombre)
  );
  
create table permisos (
	id int auto_increment primary key,
    id_empleado int,
    id_permiso int,
    fecha_inicio Date,
    fecha_fin Date,
    duracion int(5),
    aprobado boolean default false,
    FOREIGN KEY (id_empleado) REFERENCES empleado(id) ON DELETE NO ACTION ON UPDATE NO ACTION,
    FOREIGN KEY (id_permiso) REFERENCES tipo_permiso(id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

create table registro (
	id int auto_increment primary key,
    id_empleado int,
    fecha_inicio DateTime not null,
    fecha_fin DateTime not null,
    FOREIGN KEY (id_empleado) REFERENCES empleado(id) ON DELETE NO ACTION ON UPDATE NO ACTION
);
