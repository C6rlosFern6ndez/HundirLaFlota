system cls;

-- Eliminar registros anteriores
DELETE FROM Barcos;
DELETE FROM Tableros;
DELETE FROM Partidas;
DELETE FROM Usuarios;

-- Insertar usuarios
INSERT INTO Usuarios (username, password, conectado) VALUES ('user1', 'password1', TRUE);
INSERT INTO Usuarios (username, password, conectado) VALUES ('user2', 'password2', FALSE);

-- Insertar partidas
INSERT INTO Partidas (jugador1Id, jugador2Id, estado, turno) VALUES (1, 2, 'EN_CURSO', 1);

-- Insertar tableros
INSERT INTO Tableros (partidaId, jugadorId, tablero) VALUES (1, 1, 'tablero1');
INSERT INTO Tableros (partidaId, jugadorId, tablero) VALUES (1, 2, 'tablero2');

-- Insertar barcos
INSERT INTO Barcos (tableroId, tipo, tamano, columnaInicio, filaInicio, columnaFin, filaFin, hundido)
VALUES (1, 'PORTAAVIONES', 5, 'A', 1, 'A', 5, FALSE);

INSERT INTO Barcos (tableroId, tipo, tamano, columnaInicio, filaInicio, columnaFin, filaFin, hundido)
VALUES (1, 'ACORAZADO', 4, 'B', 1, 'B', 4, FALSE);

INSERT INTO Barcos (tableroId, tipo, tamano, columnaInicio, filaInicio, columnaFin, filaFin, hundido)
VALUES (1, 'SUBMARINO', 3, 'C', 1, 'C', 3, FALSE);

INSERT INTO Barcos (tableroId, tipo, tamano, columnaInicio, filaInicio, columnaFin, filaFin, hundido)
VALUES (1, 'DESTRUCTOR', 2, 'D', 1, 'D', 2, FALSE);

-- Insertar movimientos
INSERT INTO Movimientos (partidaId, jugadorId, columna, fila, impacto, resultado, turno)
VALUES (1, 1, 'A', 1, TRUE, 'TOCADO', 1);

INSERT INTO Movimientos (partidaId, jugadorId, columna, fila, impacto, resultado, turno)
VALUES (1, 2, 'B', 2, FALSE, 'AGUA', 2);