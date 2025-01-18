package com.mycompany.hundirlaflotaserver;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "Barcos")
public class BarcoEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "tablero_id", nullable = false)
    private TableroEntity tablero;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoBarco tipo;

    @Column(nullable = false)
    private int tamano;

    @Column(nullable = false)
    private char columnaInicio;

    @Column(nullable = false)
    private int filaInicio;

    @Column(nullable = false)
    private char columnaFin;

    @Column(nullable = false)
    private int filaFin;

    @Column(nullable = false)
    private boolean hundido;

    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TableroEntity getTablero() {
        return tablero;
    }

    public void setTablero(TableroEntity tablero) {
        this.tablero = tablero;
    }

    public TipoBarco getTipo() {
        return tipo;
    }

    public void setTipo(TipoBarco tipo) {
        this.tipo = tipo;
    }

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

    public char getColumnaInicio() {
        return columnaInicio;
    }

    public void setColumnaInicio(char columnaInicio) {
        this.columnaInicio = columnaInicio;
    }

    public int getFilaInicio() {
        return filaInicio;
    }

    public void setFilaInicio(int filaInicio) {
        this.filaInicio = filaInicio;
    }

    public char getColumnaFin() {
        return columnaFin;
    }

    public void setColumnaFin(char columnaFin) {
        this.columnaFin = columnaFin;
    }

    public int getFilaFin() {
        return filaFin;
    }

    public void setFilaFin(int filaFin) {
        this.filaFin = filaFin;
    }

    public boolean isHundido() {
        return hundido;
    }

    public void setHundido(boolean hundido) {
        this.hundido = hundido;
    }

    public enum TipoBarco {
        PORTAAVIONES, ACORAZADO, SUBMARINO, DESTRUCTOR
    }
}