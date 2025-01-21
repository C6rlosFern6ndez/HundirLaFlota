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
            MenuDeConsultas menu = new MenuDeConsultas(cliente);
            menu.mostrarMenu();
            cliente.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}