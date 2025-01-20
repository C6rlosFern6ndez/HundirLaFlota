package com.mycompany.hundirlaflotaserver;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Usuarios")
public class UsuarioEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean conectado;

    @OneToMany(mappedBy = "jugador1", cascade = CascadeType.ALL)
    private List<PartidaEntity> partidasComoJugador1;

    @OneToMany(mappedBy = "jugador2", cascade = CascadeType.ALL)
    private List<PartidaEntity> partidasComoJugador2;

    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isConectado() {
        return conectado;
    }

    public void setConectado(boolean conectado) {
        this.conectado = conectado;
    }

    public List<PartidaEntity> getPartidasComoJugador1() {
        return partidasComoJugador1;
    }

    public void setPartidasComoJugador1(List<PartidaEntity> partidasComoJugador1) {
        this.partidasComoJugador1 = partidasComoJugador1;
    }

    public List<PartidaEntity> getPartidasComoJugador2() {
        return partidasComoJugador2;
    }

    public void setPartidasComoJugador2(List<PartidaEntity> partidasComoJugador2) {
        this.partidasComoJugador2 = partidasComoJugador2;
    }
    
}