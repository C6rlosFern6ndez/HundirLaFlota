package com.mycompany.hundirlaflotacliente;

import java.io.IOException;

public class GestorDePartidas {
    private Cliente cliente;

    public GestorDePartidas(Cliente cliente) {
        this.cliente = cliente;
    }

    public void empezarNuevaPartida() throws IOException {
        String response = cliente.sendMessage("NUEVA_PARTIDA");
        System.out.println("Respuesta del servidor: " + response);
    }

    public void verPartidasTerminadas() throws IOException {
        String response = cliente.sendMessage("VER_PARTIDAS_TERMINADAS");
        System.out.println("Respuesta del servidor: " + response);
    }

    public void verPartidasEnCurso() throws IOException {
        String response = cliente.sendMessage("VER_PARTIDAS_EN_CURSO");
        System.out.println("Respuesta del servidor: " + response);
    }

    public void renunciarAPartida() throws IOException {
        String response = cliente.sendMessage("RENUNCIAR_PARTIDA");
        System.out.println("Respuesta del servidor: " + response);
    }
}