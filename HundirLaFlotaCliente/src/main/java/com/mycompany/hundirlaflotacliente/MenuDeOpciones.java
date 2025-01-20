package com.mycompany.hundirlaflotacliente;

import java.io.IOException;
import java.util.Scanner;

public class MenuDeOpciones {
    private Cliente cliente;

    public MenuDeOpciones(Cliente cliente) {
        this.cliente = cliente;
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Empezar nueva partida");
            System.out.println("2. Ver partidas terminadas");
            System.out.println("3. Ver partidas sin terminar y disparar");
            System.out.println("4. Renunciar a una partida");
            System.out.println("5. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            try {
                switch (opcion) {
                    case 1:
                        empezarNuevaPartida(scanner);
                        break;
                    case 2:
                        verPartidasTerminadas();
                        break;
                    case 3:
                        verPartidasSinTerminarYDisparar(scanner);
                        break;
                    case 4:
                        renunciarAPartida(scanner);
                        break;
                    case 5:
                        cliente.disconnect();
                        System.out.println("Desconectado del servidor");
                        return;
                    default:
                        System.out.println("Opción no válida");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void empezarNuevaPartida(Scanner scanner) throws IOException {
        System.out.println("Ingrese el nombre del primer jugador:");
        String jugador1 = scanner.nextLine();
        System.out.println("Ingrese la contraseña del primer jugador:");
        String password1 = scanner.nextLine();

        System.out.println("Ingrese el nombre del segundo jugador:");
        String jugador2 = scanner.nextLine();
        System.out.println("Ingrese la contraseña del segundo jugador:");
        String password2 = scanner.nextLine();

        String response1 = cliente.sendMessage("LOGIN " + jugador1 + " " + password1);
        if (!response1.equals("LOGIN_SUCCESS")) {
            System.out.println("Error: " + response1);
            return;
        }

        String response2 = cliente.sendMessage("LOGIN " + jugador2 + " " + password2);
        if (!response2.equals("LOGIN_SUCCESS")) {
            System.out.println("Error: " + response2);
            return;
        }

        String response = cliente.sendMessage("START_GAME " + jugador1 + " " + jugador2);
        System.out.println("Respuesta del servidor: " + response);
    }

    private void verPartidasTerminadas() throws IOException {
        String response = cliente.sendMessage("VIEW_FINISHED_GAMES");
        if (response != null && response.startsWith("FINISHED_GAMES")) {
            String[] lines = response.split("\n");
            for (int i = 1; i < lines.length; i++) {
                System.out.println(lines[i]);
            }
        } else {
            System.out.println("Error: " + (response != null ? response : "No response from server"));
        }
    }

    private void verPartidasSinTerminarYDisparar(Scanner scanner) throws IOException {
        System.out.println("Ingrese el ID de la partida:");
        int partidaId = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea
        System.out.println("Ingrese la columna:");
        char columna = scanner.nextLine().charAt(0);
        System.out.println("Ingrese la fila:");
        int fila = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea
        String response = cliente.sendMessage("MAKE_MOVE " + partidaId + " " + columna + " " + fila);
        System.out.println("Respuesta del servidor: " + response);
    }

    private void renunciarAPartida(Scanner scanner) throws IOException {
        System.out.println("Ingrese el ID de la partida:");
        int partidaId = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea
        String response = cliente.sendMessage("RESIGN_GAME " + partidaId);
        System.out.println("Respuesta del servidor: " + response);
    }
}