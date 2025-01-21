package com.mycompany.hundirlaflotacliente;

import java.io.*;
import java.net.Socket;

public class Cliente {
    private String host;
    private int port;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public Cliente(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void connect() throws IOException {
        socket = new Socket(host, port);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream(), true);
    }

    public void disconnect() throws IOException {
        reader.close();
        writer.close();
        socket.close();
    }

    public String sendMessage(String message) throws IOException {
        writer.println(message);
        return reader.readLine();
    }
}