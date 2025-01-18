package com.mycompany.hundirlaflotaserver;

import java.io.Serializable;
import javax.persistence.*;

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
    
    // To String
    @Override
    public String toString() {
        return "UsuarioEntity{" + "id=" + id + ", username=" + username + ", password=" + password + ", conectado=" + conectado + '}';
    }
    
    
}