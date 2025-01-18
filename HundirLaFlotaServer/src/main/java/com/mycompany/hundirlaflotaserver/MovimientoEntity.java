package com.mycompany.hundirlaflotaserver;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Movimientos")
public class MovimientoEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "partida_id", nullable = false)
    private PartidaEntity partida;

    @ManyToOne
    @JoinColumn(name = "jugador_id", nullable = false)
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date timestamp;

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

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    

    public enum ResultadoMovimiento {
        AGUA, TOCADO, HUNDIDO
    }

    @Override
    public String toString() {
        return "MovimientoEntity{" + "id=" + id + ", partida=" + partida + ", jugador=" + jugador + ", columna=" + columna + ", fila=" + fila + ", impacto=" + impacto + ", resultado=" + resultado + ", turno=" + turno + ", timestamp=" + timestamp + '}';
    }
    
    
}