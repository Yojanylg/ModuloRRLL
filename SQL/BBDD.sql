-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 15-05-2025 a las 11:22:17
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `tfc`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--
CREATE DATABASE tfc;

USE tfc;

CREATE TABLE `empleado` (
  `id_empleado` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellido_1` varchar(50) NOT NULL,
  `apellido_2` varchar(50) DEFAULT NULL,
  `dni` varchar(9) NOT NULL,
  `email` varchar(50) NOT NULL,
  `upassword` varchar(250) NOT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `fecha_alta` date DEFAULT NULL,
  `fecha_baja` date DEFAULT NULL,
  `id_jefe` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`id_empleado`, `nombre`, `apellido_1`, `apellido_2`, `dni`, `email`, `upassword`, `direccion`, `fecha_alta`, `fecha_baja`, `id_jefe`) VALUES
(1, 'Juan', 'Pérez', 'Gómez', '12345678A', 'juan.perez@example.com', 'pass1234', 'Calle Falsa 123', '2024-01-15', NULL, NULL),
(2, 'María', 'López', NULL, '23456789B', 'maria.lopez@example.com', 'pass5678', 'Av. Siempre Viva 742', '2024-02-01', NULL, 1),
(3, 'Carlos', 'Martínez', 'Ruiz', '34567890C', 'carlos.martinez@example.com', 'pass9012', 'Calle Luna 45', '2024-02-10', NULL, 1),
(4, 'Ana', 'García', 'Santos', '45678901D', 'ana.garcia@example.com', 'pass3456', 'Paseo del Sol 12', '2024-03-05', NULL, 2),
(5, 'Luis', 'Sánchez', NULL, '56789012E', 'luis.sanchez@example.com', 'pass7890', NULL, '2024-03-20', NULL, 2),
(6, 'Elena', 'Torres', 'Blanco', '67890123F', 'elena.torres@example.com', 'pass2345', 'Calle Verde 89', '2024-04-01', NULL, 3),
(7, 'Pedro', 'Ramírez', NULL, '78901234G', 'pedro.ramirez@example.com', 'pass6789', NULL, '2024-04-15', NULL, 3),
(8, 'Lucía', 'Hernández', 'Mora', '89012345H', 'lucia.hernandez@example.com', 'pass1122', 'Camino Rojo 56', '2024-05-01', NULL, 4),
(9, 'Jorge', 'Ortega', 'Díaz', '90123456I', 'jorge.ortega@example.com', 'pass3344', 'Av. Azul 101', '2024-05-10', NULL, 4),
(10, 'Sara', 'Vega', NULL, '01234567J', 'sara.vega@example.com', 'pass5566', 'Calle Blanca 78', '2024-05-20', NULL, 5),
(12, 'Pedro', 'García', NULL, '02345678A', 'pedro@empresa.com', 'pass1234', 'Calle Falsa 123, 28013 Madrid', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_permiso`
--

CREATE TABLE `tipo_permiso` (
  `id_tipo_permiso` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `duracion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tipo_permiso`
--

INSERT INTO `tipo_permiso` (`id_tipo_permiso`, `nombre`, `descripcion`, `duracion`) VALUES
(1, 'vacaciones', NULL, 28),
(2, 'asuntos propios', 'no requiere justificacion', 5),
(3, 'mudanza', 'requiere presentar evidencia', 1);

--
-- Estructura de tabla para la tabla `permisos`
--

CREATE TABLE `permisos` (
  `id_permiso` int(11) NOT NULL,
  `id_empleado` int(11) DEFAULT NULL,
  `id_tipo_permiso` int(11) DEFAULT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `fecha_fin` date DEFAULT NULL,
  `duracion` int(5) DEFAULT NULL,
  `aprobado` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `permisos`
--

INSERT INTO `permisos` (`id_permiso`, `id_empleado`, `id_tipo_permiso`, `fecha_inicio`, `fecha_fin`, `duracion`, `aprobado`) VALUES
(1, 1, 1, '2025-04-01', '2025-04-02', 2, 1),
(2, 2, 2, '2025-04-03', '2025-04-03', 1, 0),
(3, 3, 3, '2025-04-05', '2025-04-07', 3, 1),
(4, 4, 1, '2025-04-10', '2025-04-10', 1, 0),
(5, 5, 2, '2025-04-12', '2025-04-13', 2, 1),
(6, 1, 3, '2025-04-14', '2025-04-14', 1, 1),
(7, 7, 1, '2025-04-15', '2025-04-17', 3, 0),
(8, 8, 2, '2025-04-18', '2025-04-19', 2, 1),
(9, 1, 1, '2020-04-14', NULL, 5, 1),
(41, 1, 1, '2025-05-26', '2025-05-31', 5, 0),
(42, 1, 2, '2025-05-12', '2025-05-13', 1, 0),
(43, 1, 2, '2025-05-12', '2025-05-17', 5, 0),
(44, 1, 2, '2025-05-20', '2025-05-22', 2, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `registro`
--

CREATE TABLE `registro` (
  `id` int(11) NOT NULL,
  `id_empleado` int(11) DEFAULT NULL,
  `fecha_inicio` datetime NOT NULL,
  `fecha_fin` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `registro`
--

INSERT INTO `registro` (`id`, `id_empleado`, `fecha_inicio`, `fecha_fin`) VALUES
(1, 1, '2025-04-05 09:45:00', '2025-04-05 18:45:00'),
(2, 1, '2025-04-21 09:45:00', '2025-04-21 18:45:00'),
(3, 1, '2025-04-17 09:30:00', '2025-04-17 18:30:00'),
(4, 1, '2025-04-25 08:45:00', '2025-04-25 14:45:00'),
(5, 2, '2025-04-23 10:15:00', '2025-04-23 16:15:00'),
(6, 2, '2025-04-14 08:00:00', '2025-04-14 14:00:00'),
(7, 2, '2025-04-16 09:45:00', '2025-04-16 16:45:00'),
(8, 2, '2025-04-25 10:45:00', '2025-04-25 19:45:00'),
(9, 3, '2025-04-01 07:00:00', '2025-04-01 13:00:00'),
(10, 3, '2025-04-22 09:00:00', '2025-04-22 15:00:00'),
(11, 3, '2025-04-10 07:30:00', '2025-04-10 13:30:00'),
(12, 3, '2025-04-22 08:30:00', '2025-04-22 16:30:00'),
(13, 4, '2025-04-19 08:45:00', '2025-04-19 17:45:00'),
(50, 1, '2025-05-11 19:15:49', '2025-05-11 19:15:51'),
(51, 1, '2025-05-11 19:16:54', '2025-05-11 19:16:55'),
(52, 1, '2025-05-11 19:20:13', '2025-05-11 19:20:16'),
(53, 1, '2025-05-14 23:28:00', '2025-05-14 23:28:03'),
(54, 1, '2025-05-14 23:36:47', '2025-05-14 23:36:54');

-- --------------------------------------------------------

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`id_empleado`),
  ADD UNIQUE KEY `dni` (`dni`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `id` (`id_empleado`),
  ADD KEY `dni_2` (`dni`),
  ADD KEY `email_2` (`email`),
  ADD KEY `id_jefe` (`id_jefe`);

--
-- Indices de la tabla `permisos`
--
ALTER TABLE `permisos`
  ADD PRIMARY KEY (`id_permiso`),
  ADD KEY `id_empleado` (`id_empleado`),
  ADD KEY `id_permiso` (`id_tipo_permiso`);

--
-- Indices de la tabla `registro`
--
ALTER TABLE `registro`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_empleado` (`id_empleado`);

--
-- Indices de la tabla `tipo_permiso`
--
ALTER TABLE `tipo_permiso`
  ADD PRIMARY KEY (`id_tipo_permiso`),
  ADD UNIQUE KEY `nombre` (`nombre`),
  ADD KEY `nombre_2` (`nombre`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `empleado`
--
ALTER TABLE `empleado`
  MODIFY `id_empleado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `permisos`
--
ALTER TABLE `permisos`
  MODIFY `id_permiso` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT de la tabla `registro`
--
ALTER TABLE `registro`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;

--
-- AUTO_INCREMENT de la tabla `tipo_permiso`
--
ALTER TABLE `tipo_permiso`
  MODIFY `id_tipo_permiso` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD CONSTRAINT `empleado_ibfk_1` FOREIGN KEY (`id_jefe`) REFERENCES `empleado` (`id_empleado`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `permisos`
--
ALTER TABLE `permisos`
  ADD CONSTRAINT `permisos_ibfk_1` FOREIGN KEY (`id_empleado`) REFERENCES `empleado` (`id_empleado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `permisos_ibfk_2` FOREIGN KEY (`id_tipo_permiso`) REFERENCES `tipo_permiso` (`id_tipo_permiso`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `registro`
--
ALTER TABLE `registro`
  ADD CONSTRAINT `registro_ibfk_1` FOREIGN KEY (`id_empleado`) REFERENCES `empleado` (`id_empleado`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
