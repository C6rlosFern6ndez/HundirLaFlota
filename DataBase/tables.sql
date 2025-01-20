-- DROP TABLE IF EXISTS Conexiones;
-- DROP TABLE IF EXISTS Movimientos;
-- DROP TABLE IF EXISTS Barcos;
-- DROP TABLE IF EXISTS Tableros;
-- DROP TABLE IF EXISTS Partidas;
-- DROP TABLE IF EXISTS Usuarios;
-- DROP TABLE IF EXISTS ConfiguracionBarcos;
system cls;
DROP DATABASE IF EXISTS HundirLaFlota;

CREATE DATABASE HundirLaFlota;
USE HundirLaFlota;

CREATE TABLE Usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    conectado BOOLEAN DEFAULT FALSE
);

CREATE TABLE Partidas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    jugador1Id INT NOT NULL,
    jugador2Id INT NOT NULL,
    estado VARCHAR(20) DEFAULT 'EN_CURSO',
    turno INT NOT NULL,
    fechaInicio TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fechaFin TIMESTAMP DEFAULT NULL,
    FOREIGN KEY (jugador1Id) REFERENCES Usuarios(id),
    FOREIGN KEY (jugador2Id) REFERENCES Usuarios(id)
);

CREATE TABLE Tableros (
    id INT AUTO_INCREMENT PRIMARY KEY,
    partidaId INT NOT NULL,
    jugadorId INT NOT NULL,
    tablero VARCHAR(10) NOT NULL,
    FOREIGN KEY (partidaId) REFERENCES Partidas(id),
    FOREIGN KEY (jugadorId) REFERENCES Usuarios(id)
);

CREATE TABLE Barcos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tableroId INT NOT NULL,
    tipo VARCHAR(255) NOT NULL,
    tamano INT NOT NULL,
    columnaInicio CHAR(1) NOT NULL,
    filaInicio INT NOT NULL,
    columnaFin CHAR(1) NOT NULL,
    filaFin INT NOT NULL,
    hundido BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (tableroId) REFERENCES Tableros(id)
);

CREATE TABLE Movimientos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    partidaId INT NOT NULL,
    jugadorId INT NOT NULL,
    columna CHAR(1) NOT NULL,
    fila INT NOT NULL,
    impacto BOOLEAN NOT NULL,
    resultado VARCHAR(10) NOT NULL,
    turno INT NOT NULL,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (partidaId) REFERENCES Partidas(id),
    FOREIGN KEY (jugadorId) REFERENCES Usuarios(id)
);