package com.mycompany.hundirlaflotaserver;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Partidas")
public class PartidaEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "jugador1_id", nullable = false)
    private UsuarioEntity jugador1;

    @ManyToOne
    @JoinColumn(name = "jugador2_id", nullable = false)
    private UsuarioEntity jugador2;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoPartida estado;

    @ManyToOne
    @JoinColumn(name = "ganador_id")
    private UsuarioEntity ganador;

    @Column(nullable = false)
    private int turno;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date fechaInicio;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;

    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UsuarioEntity getJugador1() {
        return jugador1;
    }

    public void setJugador1(UsuarioEntity jugador1) {
        this.jugador1 = jugador1;
    }

    public UsuarioEntity getJugador2() {
        return jugador2;
    }

    public void setJugador2(UsuarioEntity jugador2) {
        this.jugador2 = jugador2;
    }

    public EstadoPartida getEstado() {
        return estado;
    }

    public void setEstado(EstadoPartida estado) {
        this.estado = estado;
    }

    public UsuarioEntity getGanador() {
        return ganador;
    }

    public void setGanador(UsuarioEntity ganador) {
        this.ganador = ganador;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    

    public enum EstadoPartida {
        EN_CURSO, TERMINADA, ABANDONADA
    }

    @Override
    public String toString() {
        return "PartidaEntity{" + "id=" + id + ", jugador1=" + jugador1 + ", jugador2=" + jugador2 + ", estado=" + estado + ", ganador=" + ganador + ", turno=" + turno + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + '}';
    }
    
    
}