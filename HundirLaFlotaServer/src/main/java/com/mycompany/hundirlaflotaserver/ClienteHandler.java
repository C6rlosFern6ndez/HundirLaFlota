package com.mycompany.hundirlaflotaserver;

import java.io.*;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteHandler implements Runnable {
    private Socket clientSocket;
    private ServidorSocket servidor;
    private BufferedReader reader;
    private PrintWriter writer;
    private DatabaseManager dbManager;

    public ClienteHandler(Socket socket, ServidorSocket servidor) {
        this.clientSocket = socket;
        this.servidor = servidor;
        this.dbManager = servidor.getDatabaseManager();
    }

    @Override
    public void run() {
        try {
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer = new PrintWriter(clientSocket.getOutputStream(), true);

            String message;
            while ((message = reader.readLine()) != null) {
                if (message.startsWith("LOGIN")) {
                    handleLogin(message);
                } else if (message.equals("VER_USUARIOS")) {
                    verUsuarios();
                } else if (message.equals("VER_PARTIDAS")) {
                    verPartidas();
                } else if (message.equals("VER_TABLEROS")) {
                    verTableros();
                } else if (message.equals("VER_BARCOS")) {
                    verBarcos();
                } else if (message.equals("VER_MOVIMIENTOS")) {
                    verMovimientos();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
                writer.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleLogin(String message) {
        String[] parts = message.split(" ");
        String username = parts[1];
        String password = parts[2];

        try {
            ResultSet rs = dbManager.executeQuery("SELECT * FROM Usuarios WHERE username = '" + username + "' AND password = '" + password + "'");
            if (rs.next()) {
                writer.println("OK");
            } else {
                writer.println("FAIL");
            }
            dbManager.close(null, null, rs);
        } catch (SQLException e) {
            e.printStackTrace();
            writer.println("FAIL");
        }
    }

    private void verUsuarios() {
        try {
            ResultSet rs = dbManager.executeQuery("SELECT * FROM Usuarios");
            StringBuilder response = new StringBuilder("Usuarios:");
            while (rs.next()) {
                response.append("\nID: ").append(rs.getInt("id"))
                        .append(", Username: ").append(rs.getString("username"))
                        .append(", Conectado: ").append(rs.getBoolean("conectado"));
            }
            writer.println(response.toString());
            dbManager.close(null, null, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void verPartidas() {
        try {
            ResultSet rs = dbManager.executeQuery("SELECT * FROM Partidas");
            StringBuilder response = new StringBuilder("Partidas:");
            while (rs.next()) {
                response.append("\nID: ").append(rs.getInt("id"))
                        .append(", Jugador1 ID: ").append(rs.getInt("jugador1Id"))
                        .append(", Jugador2 ID: ").append(rs.getInt("jugador2Id"))
                        .append(", Estado: ").append(rs.getString("estado"))
                        .append(", Turno: ").append(rs.getInt("turno"));
            }
            writer.println(response.toString());
            dbManager.close(null, null, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void verTableros() {
        try {
            ResultSet rs = dbManager.executeQuery("SELECT * FROM Tableros");
            StringBuilder response = new StringBuilder("Tableros:");
            while (rs.next()) {
                response.append("\nID: ").append(rs.getInt("id"))
                        .append(", Partida ID: ").append(rs.getInt("partidaId"))
                        .append(", Jugador ID: ").append(rs.getInt("jugadorId"))
                        .append(", Tablero: ").append(rs.getString("tablero"));
            }
            writer.println(response.toString());
            dbManager.close(null, null, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void verBarcos() {
        try {
            ResultSet rs = dbManager.executeQuery("SELECT * FROM Barcos");
            StringBuilder response = new StringBuilder("Barcos:");
            while (rs.next()) {
                response.append("\nID: ").append(rs.getInt("id"))
                        .append(", Tablero ID: ").append(rs.getInt("tableroId"))
                        .append(", Tipo: ").append(rs.getString("tipo"))
                        .append(", Tamaño: ").append(rs.getInt("tamano"))
                        .append(", Columna Inicio: ").append(rs.getString("columnaInicio"))
                        .append(", Fila Inicio: ").append(rs.getInt("filaInicio"))
                        .append(", Columna Fin: ").append(rs.getString("columnaFin"))
                        .append(", Fila Fin: ").append(rs.getInt("filaFin"))
                        .append(", Hundido: ").append(rs.getBoolean("hundido"));
            }
            writer.println(response.toString());
            dbManager.close(null, null, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void verMovimientos() {
        try {
            ResultSet rs = dbManager.executeQuery("SELECT * FROM Movimientos");
            StringBuilder response = new StringBuilder("Movimientos:");
            while (rs.next()) {
                response.append("\nID: ").append(rs.getInt("id"))
                        .append(", Partida ID: ").append(rs.getInt("partidaId"))
                        .append(", Jugador ID: ").append(rs.getInt("jugadorId"))
                        .append(", Columna: ").append(rs.getString("columna"))
                        .append(", Fila: ").append(rs.getInt("fila"))
                        .append(", Impacto: ").append(rs.getBoolean("impacto"))
                        .append(", Resultado: ").append(rs.getString("resultado"))
                        .append(", Turno: ").append(rs.getInt("turno"))
                        .append(", Timestamp: ").append(rs.getTimestamp("timestamp"));
            }
            writer.println(response.toString());
            dbManager.close(null, null, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}