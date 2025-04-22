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

INSERT INTO empleado (nombre, apellido_1, apellido_2, dni, email, upassword, direccion, fecha_alta, fecha_baja, id_jefe) VALUES
    ('Juan', 'Pérez', 'Gómez', '12345678A', 'juan.perez@example.com', 'pass1234', 'Calle Falsa 123', '2024-01-15', NULL, NULL),
    ('María', 'López', NULL, '23456789B', 'maria.lopez@example.com', 'pass5678', 'Av. Siempre Viva 742', '2024-02-01', NULL, 1),
    ('Carlos', 'Martínez', 'Ruiz', '34567890C', 'carlos.martinez@example.com', 'pass9012', 'Calle Luna 45', '2024-02-10', NULL, 1),
    ('Ana', 'García', 'Santos', '45678901D', 'ana.garcia@example.com', 'pass3456', 'Paseo del Sol 12', '2024-03-05', NULL, 2),
    ('Luis', 'Sánchez', NULL, '56789012E', 'luis.sanchez@example.com', 'pass7890', NULL, '2024-03-20', NULL, 2),
    ('Elena', 'Torres', 'Blanco', '67890123F', 'elena.torres@example.com', 'pass2345', 'Calle Verde 89', '2024-04-01', NULL, 3),
    ('Pedro', 'Ramírez', NULL, '78901234G', 'pedro.ramirez@example.com', 'pass6789', NULL, '2024-04-15', NULL, 3),
    ('Lucía', 'Hernández', 'Mora', '89012345H', 'lucia.hernandez@example.com', 'pass1122', 'Camino Rojo 56', '2024-05-01', NULL, 4),
    ('Jorge', 'Ortega', 'Díaz', '90123456I', 'jorge.ortega@example.com', 'pass3344', 'Av. Azul 101', '2024-05-10', NULL, 4),
    ('Sara', 'Vega', NULL, '01234567J', 'sara.vega@example.com', 'pass5566', 'Calle Blanca 78', '2024-05-20', NULL, 5);

INSERT INTO tipo_permiso (nombre, descripcion, duracion) VALUES
    ('vacaciones', NULL, 28),
    ('asuntos propios', 'no requiere justificacion', 5),
    ('mudanza', 'requiere presentar evidencia', 1);

INSERT INTO permisos (id_empleado, id_permiso, fecha_inicio, fecha_fin, duracion, aprobado) VALUES
    (1, 1, '2025-04-01', '2025-04-02', 2, true),
    (2, 2, '2025-04-03', '2025-04-03', 1, false),
    (3, 3, '2025-04-05', '2025-04-07', 3, true),
    (4, 1, '2025-04-10', '2025-04-10', 1, false),
    (5, 2, '2025-04-12', '2025-04-13', 2, true),
    (1, 3, '2025-04-14', '2025-04-14', 1, true),
    (7, 1, '2025-04-15', '2025-04-17', 3, false),
    (8, 2, '2025-04-18', '2025-04-19', 2, true),
    (9, 3, '2025-04-20', '2025-04-20', 1, false),
    (10, 1, '2025-04-21', '2025-04-22', 2, true),
    (4, 1, '2025-04-10', '2025-04-10', 1, false),
    (5, 2, '2025-04-12', '2025-04-13', 2, true),
    (1, 3, '2025-04-14', '2025-04-14', 1, true),
    (7, 1, '2025-04-15', '2025-04-17', 3, false),
    (8, 2, '2025-04-18', '2025-04-19', 2, true);

INSERT INTO registro (id_empleado, fecha_inicio, fecha_fin) VALUES
    (1, '2025-04-05 09:45:00', '2025-04-05 18:45:00'),
    (1, '2025-04-21 09:45:00', '2025-04-21 18:45:00'),
    (1, '2025-04-17 09:30:00', '2025-04-17 18:30:00'),
    (1, '2025-04-25 08:45:00', '2025-04-25 14:45:00'),
    (2, '2025-04-23 10:15:00', '2025-04-23 16:15:00'),
    (2, '2025-04-14 08:00:00', '2025-04-14 14:00:00'),
    (2, '2025-04-16 09:45:00', '2025-04-16 16:45:00'),
    (2, '2025-04-25 10:45:00', '2025-04-25 19:45:00'),
    (3, '2025-04-01 07:00:00', '2025-04-01 13:00:00'),
    (3, '2025-04-22 09:00:00', '2025-04-22 15:00:00'),
    (3, '2025-04-10 07:30:00', '2025-04-10 13:30:00'),
    (3, '2025-04-22 08:30:00', '2025-04-22 16:30:00'),
    (4, '2025-04-19 08:45:00', '2025-04-19 17:45:00'),
    (4, '2025-04-13 07:00:00', '2025-04-13 14:00:00'),
    (4, '2025-04-26 10:30:00', '2025-04-26 17:30:00'),
    (4, '2025-04-07 09:15:00', '2025-04-07 16:15:00'),
    (5, '2025-04-03 10:30:00', '2025-04-03 18:30:00'),
    (5, '2025-04-12 10:45:00', '2025-04-12 18:45:00'),
    (5, '2025-04-17 09:00:00', '2025-04-17 18:00:00'),
    (5, '2025-04-08 08:00:00', '2025-04-08 14:00:00'),
    (6, '2025-04-15 07:30:00', '2025-04-15 14:30:00'),
    (6, '2025-04-04 09:15:00', '2025-04-04 18:15:00'),
    (6, '2025-04-28 08:00:00', '2025-04-28 14:00:00'),
    (6, '2025-04-06 10:00:00', '2025-04-06 19:00:00'),
    (7, '2025-04-09 07:45:00', '2025-04-09 13:45:00'),
    (7, '2025-04-11 09:30:00', '2025-04-11 17:30:00'),
    (7, '2025-04-20 08:15:00', '2025-04-20 16:15:00'),
    (7, '2025-04-29 10:00:00', '2025-04-29 19:00:00'),
    (8, '2025-04-02 07:00:00', '2025-04-02 14:00:00'),
    (8, '2025-04-18 08:30:00', '2025-04-18 15:30:00'),
    (8, '2025-04-24 09:45:00', '2025-04-24 18:45:00'),
    (8, '2025-04-30 10:15:00', '2025-04-30 19:15:00'),
    (9, '2025-04-05 09:30:00', '2025-04-05 15:30:00'),
    (9, '2025-04-14 08:15:00', '2025-04-14 16:15:00'),
    (9, '2025-04-16 07:00:00', '2025-04-16 14:00:00'),
    (9, '2025-04-22 09:45:00', '2025-04-22 18:45:00'),
    (10, '2025-04-12 10:30:00', '2025-04-12 17:30:00'),
    (10, '2025-04-15 07:15:00', '2025-04-15 13:15:00'),
    (10, '2025-04-19 08:00:00', '2025-04-19 17:00:00'),
    (10, '2025-04-27 09:30:00', '2025-04-27 18:30:00');


