package com.mycompany.hundirlaflotaserver;

import java.io.*;
import java.net.*;

public class ClienteHandler implements Runnable {
    private Socket clientSocket;
    private ServidorSocket servidor;
    private PrintWriter out;
    private BufferedReader in;

    public ClienteHandler(Socket socket, ServidorSocket servidor) {
        this.clientSocket = socket;
        this.servidor = servidor;
    }

    public void run() {
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                servidor.broadcast(inputLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }
}