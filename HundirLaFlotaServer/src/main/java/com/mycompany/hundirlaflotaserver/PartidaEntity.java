package com.mycompany.hundirlaflotaserver;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Partidas")
public class PartidaEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "jugador1Id", nullable = false)
    private UsuarioEntity jugador1;

    @ManyToOne
    @JoinColumn(name = "jugador2Id", nullable = false)
    private UsuarioEntity jugador2;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoPartida estado;

    @Column(nullable = false)
    private int turno;

    @Column(nullable = false)
    private java.sql.Timestamp fechaInicio;

    @Column
    private java.sql.Timestamp fechaFin;

    @OneToMany(mappedBy = "partida", cascade = CascadeType.ALL)
    private List<TableroEntity> tableros;

    @OneToMany(mappedBy = "partida", cascade = CascadeType.ALL)
    private List<MovimientoEntity> movimientos;

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

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public Timestamp getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Timestamp fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Timestamp getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Timestamp fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<TableroEntity> getTableros() {
        return tableros;
    }

    public void setTableros(List<TableroEntity> tableros) {
        this.tableros = tableros;
    }

    public List<MovimientoEntity> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<MovimientoEntity> movimientos) {
        this.movimientos = movimientos;
    }
    
}