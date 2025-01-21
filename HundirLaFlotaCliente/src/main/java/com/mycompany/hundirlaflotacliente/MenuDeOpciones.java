package com.mycompany.hundirlaflotacliente;

import java.io.IOException;
import java.util.Scanner;

public class MenuDeOpciones {
    private Cliente cliente;
    private Scanner scanner;
    private GestorDePartidas gestorDePartidas;

    public MenuDeOpciones(Cliente cliente) {
        this.cliente = cliente;
        this.scanner = new Scanner(System.in);
        this.gestorDePartidas = new GestorDePartidas(cliente);
    }

    public void mostrarMenu() throws IOException {
        while (true) {
            System.out.println("1. Empezar nueva partida");
            System.out.println("2. Ver partidas terminadas");
            System.out.println("3. Ver partidas en curso");
            System.out.println("4. Renunciar a una partida");
            System.out.println("5. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (opcion) {
                case 1:
                    gestorDePartidas.empezarNuevaPartida();
                    break;
                case 2:
                    gestorDePartidas.verPartidasTerminadas();
                    break;
                case 3:
                    gestorDePartidas.verPartidasEnCurso();
                    break;
                case 4:
                    gestorDePartidas.renunciarAPartida();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }
}