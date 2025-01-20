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
    @JoinColumn(name = "tableroId", nullable = false)
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

    // Constructor
    public BarcoEntity(TipoBarco tipo, TableroEntity tablero, char columnaInicio, int filaInicio, char columnaFin, int filaFin) {
        this.tipo = tipo;
        this.tablero = tablero;
        this.columnaInicio = columnaInicio;
        this.filaInicio = filaInicio;
        this.columnaFin = columnaFin;
        this.filaFin = filaFin;
        this.hundido = false;

        switch (tipo) {
            case PORTAAVIONES:
                this.tamano = 5;
                break;
            case ACORAZADO:
                this.tamano = 4;
                break;
            case SUBMARINO:
                this.tamano = 3;
                break;
            case DESTRUCTOR:
                this.tamano = 2;
                break;
            default:
                throw new IllegalArgumentException("Tipo de barco no válido");
        }
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public TipoBarco getTipo() {
        return tipo;
    }

    public int getTamano() {
        return tamano;
    }

    public char getColumnaInicio() {
        return columnaInicio;
    }

    public int getFilaInicio() {
        return filaInicio;
    }

    public char getColumnaFin() {
        return columnaFin;
    }

    public int getFilaFin() {
        return filaFin;
    }

    public boolean isHundido() {
        return hundido;
    }

    public void setHundido(boolean hundido) {
        this.hundido = hundido;
    }

    public TableroEntity getTablero() {
        return tablero;
    }

    public void setTablero(TableroEntity tablero) {
        this.tablero = tablero;
    }
}