package com.mycompany.hundirlaflotaserver;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "Tableros")
public class TableroEntity implements Serializable {
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
    private String tablero;

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

    @Override
    public String toString() {
        return "TableroEntity{" + "id=" + id + ", partida=" + partida + ", jugador=" + jugador + ", tablero=" + tablero + '}';
    }
    
    
    
}