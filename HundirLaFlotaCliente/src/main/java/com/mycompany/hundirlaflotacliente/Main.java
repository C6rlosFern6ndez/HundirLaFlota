package com.mycompany.hundirlaflotacliente;

import java.io.IOException;

/**
 * Autor: Carlos Fernandez Gonzalez
 */
public class Main {

    public static void main(String[] args) {
        Cliente cliente = new Cliente("localhost", 8080);
        try {
            cliente.connect();
            System.out.println("Conectado al servidor");

            // Ejemplo de autenticación
            String response = cliente.sendMessage("LOGIN Carlos 1234");
            System.out.println("Respuesta del servidor: " + response);

            MenuDeOpciones menu = new MenuDeOpciones(cliente);
            menu.mostrarMenu();

            cliente.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}