-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 18-05-2023 a las 03:38:34
-- Versión del servidor: 10.4.21-MariaDB
-- Versión de PHP: 8.0.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `hotel_alura`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `huespedes`
--

CREATE TABLE `huespedes` (
  `id` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `apellido` varchar(70) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `nacionalidad` varchar(30) NOT NULL,
  `telefono` varchar(20) NOT NULL,
  `id_reserva` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `huespedes`
--

INSERT INTO `huespedes` (`id`, `nombre`, `apellido`, `fecha_nacimiento`, `nacionalidad`, `telefono`, `id_reserva`) VALUES
(1, 'Marina ', 'De Simone', '1973-12-13', 'argentino-argentina', '11593405556', 8),
(5, 'Guadalupe', 'Osorio', '2012-05-09', 'argentino-argentina', '4354534534', 12);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservas`
--

CREATE TABLE `reservas` (
  `id` int(11) NOT NULL,
  `fechaEntrada` date NOT NULL,
  `fechaSalida` date NOT NULL,
  `valor` varchar(100) NOT NULL,
  `FormaPago` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `reservas`
--

INSERT INTO `reservas` (`id`, `fechaEntrada`, `fechaSalida`, `valor`, `FormaPago`) VALUES
(2, '2023-05-13', '2023-05-18', '  $2500', 'Tarjeta de Débito'),
(3, '2023-05-13', '2023-05-19', '  $3000', 'Tarjeta de Débito'),
(4, '2023-05-13', '2023-05-20', '  $3500', 'Tarjeta de Débito'),
(5, '2023-05-25', '2023-06-15', '  $10500', 'Tarjeta de Crédito'),
(6, '2023-05-23', '2023-05-28', '  $2500', 'Dinero en efectivo'),
(7, '2023-05-28', '2023-05-31', '  $1500', 'Tarjeta de Débito'),
(8, '2023-05-13', '2023-05-19', '  $3000', 'Dinero en efectivo'),
(9, '2023-05-28', '2023-06-30', '  $16500', 'Tarjeta de Débito'),
(12, '2023-05-31', '2023-08-26', '  $43500', 'Tarjeta de Crédito');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `usuario` varchar(30) NOT NULL,
  `clave` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `usuario`, `clave`) VALUES
(1, 'MDS', 'dePelos');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `huespedes`
--
ALTER TABLE `huespedes`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `reservas`
--
ALTER TABLE `reservas`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `huespedes`
--
ALTER TABLE `huespedes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `reservas`
--
ALTER TABLE `reservas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
