package com.mycompany.hundirlaflotaserver;

import com.mycompany.hundirlaflotaserver.service.UsuarioService;
import com.mycompany.hundirlaflotaserver.service.GameService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * Autor: Carlos Fernandez Gonzalez
 */

public class Main {
    private static final int PORT = 8080;

    public static void main(String[] args) {
        UsuarioService usuarioService = new UsuarioService();
        GameService gameService = new GameService();

        try {
            System.out.println("Usuarios registrados:");
            usuarioService.getAllUsuarios().forEach(u -> System.out.println(u.getUsername()));
        } catch (Exception e) {
            System.err.println("Error during user operations: " + e.getMessage());
            e.printStackTrace();
        }

        // Iniciar el servidor
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor iniciado en el puerto " + PORT);
            System.out.println("Esperando cliente...");
            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    new ClienteHandler(clientSocket, gameService).start();
                } catch (IOException e) {
                    System.err.println("Error accepting client connection: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.err.println("Error starting server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}