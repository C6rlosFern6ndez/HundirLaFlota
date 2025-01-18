package com.mycompany.hundirlaflotaserver.service;

import com.mycompany.hundirlaflotaserver.*;

public class GameService {

    private UsuarioService usuarioService = new UsuarioService();
    private PartidaService partidaService = new PartidaService();
    private TableroService tableroService = new TableroService();
    private BarcoService barcoService = new BarcoService();
    private MovimientoService movimientoService = new MovimientoService();

    public void iniciarNuevaPartida(int jugador1Id, int jugador2Id) {
        PartidaEntity partida = new PartidaEntity();
        partida.setJugador1(usuarioService.getUsuarioById(jugador1Id));
        partida.setJugador2(usuarioService.getUsuarioById(jugador2Id));
        partida.setEstado(PartidaEntity.EstadoPartida.EN_CURSO);
        partida.setTurno(1);
        partidaService.savePartida(partida);

        // Crear tableros para los jugadores
        TableroEntity tablero1 = new TableroEntity();
        tablero1.setPartida(partida);
        tablero1.setJugador(usuarioService.getUsuarioById(jugador1Id));
        tablero1.setTablero("tablero_jugador1");
        tableroService.saveTablero(tablero1);

        TableroEntity tablero2 = new TableroEntity();
        tablero2.setPartida(partida);
        tablero2.setJugador(usuarioService.getUsuarioById(jugador2Id));
        tablero2.setTablero("tablero_jugador2");
        tableroService.saveTablero(tablero2);
    }

    public void realizarMovimiento(int partidaId, int jugadorId, char columna, int fila) {
        PartidaEntity partida = partidaService.getPartidaById(partidaId);
        if (partida.getTurno() == jugadorId) {
            MovimientoEntity movimiento = new MovimientoEntity();
            movimiento.setPartida(partida);
            movimiento.setJugador(usuarioService.getUsuarioById(jugadorId));
            movimiento.setColumna(columna);
            movimiento.setFila(fila);
            movimiento.setImpacto(false); // Lógica para determinar si hubo impacto
            movimiento.setResultado(MovimientoEntity.ResultadoMovimiento.AGUA); // Lógica para determinar el resultado
            movimiento.setTurno(partida.getTurno());
            movimientoService.saveMovimiento(movimiento);

            // Actualizar el turno de la partida
            partida.setTurno(partida.getTurno() == partida.getJugador1().getId() ? partida.getJugador2().getId() : partida.getJugador1().getId());
            partidaService.updatePartida(partida);
        }
    }
}