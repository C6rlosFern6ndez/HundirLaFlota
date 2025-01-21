package com.mycompany.hundirlaflotacliente;

import java.io.IOException;
import java.util.Scanner;

public class MenuDeConsultas {
    private Cliente cliente;
    private Scanner scanner;

    public MenuDeConsultas(Cliente cliente) {
        this.cliente = cliente;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() throws IOException {
        while (true) {
            System.out.println("1. Ver usuarios");
            System.out.println("2. Ver partidas");
            System.out.println("3. Ver tableros");
            System.out.println("4. Ver barcos");
            System.out.println("5. Ver movimientos");
            System.out.println("6. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (opcion) {
                case 1:
                    verUsuarios();
                    break;
                case 2:
                    verPartidas();
                    break;
                case 3:
                    verTableros();
                    break;
                case 4:
                    verBarcos();
                    break;
                case 5:
                    verMovimientos();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    private void verUsuarios() throws IOException {
        String response = cliente.sendMessage("VER_USUARIOS");
        System.out.println(response);
    }

    private void verPartidas() throws IOException {
        String response = cliente.sendMessage("VER_PARTIDAS");
        System.out.println(response);
    }

    private void verTableros() throws IOException {
        String response = cliente.sendMessage("VER_TABLEROS");
        System.out.println(response);
    }

    private void verBarcos() throws IOException {
        String response = cliente.sendMessage("VER_BARCOS");
        System.out.println(response);
    }

    private void verMovimientos() throws IOException {
        String response = cliente.sendMessage("VER_MOVIMIENTOS");
        System.out.println(response);
    }
}