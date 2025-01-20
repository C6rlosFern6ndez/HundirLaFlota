package com.mycompany.hundirlaflotaserver;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.*;

@Entity
@Table(name = "Movimientos")
public class MovimientoEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "partidaId", nullable = false)
    private PartidaEntity partida;

    @ManyToOne
    @JoinColumn(name = "jugadorId", nullable = false)
    private UsuarioEntity jugador;

    @Column(nullable = false)
    private char columna;

    @Column(nullable = false)
    private int fila;

    @Column(nullable = false)
    private boolean impacto;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ResultadoMovimiento resultado;

    @Column(nullable = false)
    private int turno;

    @Column(nullable = false)
    private java.sql.Timestamp timestamp;

    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PartidaEntity getPartida() {
        return partida;
    }

    public void setPartida(PartidaEntity partida) {
        this.partida = partida;
    }

    public UsuarioEntity getJugador() {
        return jugador;
    }

    public void setJugador(UsuarioEntity jugador) {
        this.jugador = jugador;
    }

    public char getColumna() {
        return columna;
    }

    public void setColumna(char columna) {
        this.columna = columna;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public boolean isImpacto() {
        return impacto;
    }

    public void setImpacto(boolean impacto) {
        this.impacto = impacto;
    }

    public ResultadoMovimiento getResultado() {
        return resultado;
    }

    public void setResultado(ResultadoMovimiento resultado) {
        this.resultado = resultado;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
    
}