package com.mycompany.hundirlaflotaserver;

import java.net.*;
import java.io.*;
import java.util.*;

public class ServidorSocket {
    private ServerSocket serverSocket;
    private List<ClienteHandler> clientes;
    private DatabaseManager dbManager;

    public ServidorSocket(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        clientes = new ArrayList<>();
        dbManager = new DatabaseManager();
    }

    public DatabaseManager getDatabaseManager() {
        return dbManager;
    }

    public void start() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                ClienteHandler clienteHandler = new ClienteHandler(clientSocket, this);
                clientes.add(clienteHandler);
                new Thread(clienteHandler).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            ServidorSocket servidor = new ServidorSocket(12345);
            servidor.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}