--Partidas y sus jugadores
SELECT p.id AS partida_id, 
       u1.username AS jugador1, 
       u2.username AS jugador2, 
       p.estado, 
       p.turno, 
       p.fecha_inicio, 
       p.fecha_fin
FROM Partidas p
JOIN Usuarios u1 ON p.jugador1_id = u1.id
JOIN Usuarios u2 ON p.jugador2_id = u2.id;

--Tableros y sus jugadores
SELECT t.id AS tablero_id, 
       t.partida_id, 
       u.username AS jugador, 
       t.tablero
FROM Tableros t
JOIN Usuarios u ON t.jugador_id = u.id;

--Barcos y sus tableros
SELECT b.id AS barco_id, 
       t.id AS tablero_id, 
       t.partida_id, 
       u.username AS jugador, 
       b.tipo, 
       b.tamano, 
       b.columna_inicio, 
       b.fila_inicio, 
       b.columna_fin, 
       b.fila_fin, 
       b.hundido
FROM Barcos b
JOIN Tableros t ON b.tablero_id = t.id
JOIN Usuarios u ON t.jugador_id = u.id;

--Partidas de un jugador
SELECT p.id AS partida_id, 
       u1.username AS jugador1, 
       u2.username AS jugador2, 
       p.estado, 
       p.turno, 
       p.fecha_inicio, 
       p.fecha_fin
FROM Partidas p
JOIN Usuarios u1 ON p.jugador1_id = u1.id
JOIN Usuarios u2 ON p.jugador2_id = u2.id
WHERE u1.username = 'Carlos' OR u2.username = 'Carlos';

--Barcos de una partida
SELECT b.id AS barco_id, 
       t.id AS tablero_id, 
       t.partida_id, 
       u.username AS jugador, 
       b.tipo, 
       b.tamano, 
       b.columna_inicio, 
       b.fila_inicio, 
       b.columna_fin, 
       b.fila_fin, 
       b.hundido
FROM Barcos b
JOIN Tableros t ON b.tablero_id = t.id
JOIN Usuarios u ON t.jugador_id = u.id
WHERE t.partida_id = 1;

--Estado de todas las partidas y sus jugadores
SELECT p.id AS partida_id, 
       u1.username AS jugador1, 
       u2.username AS jugador2, 
       p.estado, 
       p.turno, 
       p.fecha_inicio, 
       p.fecha_fin
FROM Partidas p
JOIN Usuarios u1 ON p.jugador1_id = u1.id
JOIN Usuarios u2 ON p.jugador2_id = u2.id;