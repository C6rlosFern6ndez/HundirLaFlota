package com.mycompany.hundirlaflotaserver;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Tableros")
public class TableroEntity implements Serializable {
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
    private String tablero;

    @OneToMany(mappedBy = "tablero", cascade = CascadeType.ALL)
    private List<BarcoEntity> barcos;

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

    public String getTablero() {
        return tablero;
    }

    public void setTablero(String tablero) {
        this.tablero = tablero;
    }

    public List<BarcoEntity> getBarcos() {
        return barcos;
    }

    public void setBarcos(List<BarcoEntity> barcos) {
        this.barcos = barcos;
    }
    
}