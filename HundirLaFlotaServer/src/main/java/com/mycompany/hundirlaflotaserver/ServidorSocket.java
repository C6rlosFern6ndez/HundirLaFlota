package com.mycompany.hundirlaflotaserver;

import java.io.*;
import java.net.*;
import java.util.*;

public class ServidorSocket {
    private ServerSocket serverSocket;
    private List<ClienteHandler> clientes;

    public ServidorSocket(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        clientes = new ArrayList<>();
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

    public synchronized void broadcast(String message) {
        for (ClienteHandler cliente : clientes) {
            cliente.sendMessage(message);
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