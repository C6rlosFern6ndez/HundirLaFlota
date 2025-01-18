package com.mycompany.hundirlaflotaserver;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Conexiones")
public class ConexionEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity usuario;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date fechaConexion;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDesconexion;

    // Getters y Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public void setFechaConexion(Date fechaConexion) {
        this.fechaConexion = fechaConexion;
    }

    public void setFechaDesconexion(Date fechaDesconexion) {
        this.fechaDesconexion = fechaDesconexion;
    }

    @Override
    public String toString() {
        return "ConexionEntity{" + "id=" + id + ", usuario=" + usuario + ", fechaConexion=" + fechaConexion + ", fechaDesconexion=" + fechaDesconexion + '}';
    }
    
    
}