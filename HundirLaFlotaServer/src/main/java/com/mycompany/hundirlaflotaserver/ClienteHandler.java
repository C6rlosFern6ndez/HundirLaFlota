package com.mycompany.hundirlaflotaserver;

import com.mycompany.hundirlaflotaserver.service.GameService;
import com.mycompany.hundirlaflotaserver.service.UsuarioService;

import java.io.*;
import java.net.Socket;

public class ClienteHandler extends Thread {
    private Socket clientSocket;
    private GameService gameService;
    private UsuarioService usuarioService;
    private UsuarioEntity usuario;

    public ClienteHandler(Socket socket, GameService gameService) {
        this.clientSocket = socket;
        this.gameService = gameService;
        this.usuarioService = new UsuarioService();
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                String[] tokens = inputLine.split(" ");
                String command = tokens[0];

                switch (command) {
                    case "LOGIN":
                        handleLogin(tokens, out);
                        break;
                    case "START_GAME":
                        handleStartGame(tokens, out);
                        break;
                    case "MAKE_MOVE":
                        handleMakeMove(tokens, out);
                        break;
                    case "LOGOUT":
                        handleLogout(out);
                        break;
                    default:
                        out.println("Comando no reconocido");
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleLogin(String[] tokens, PrintWriter out) {
        if (tokens.length != 3) {
            out.println("Uso: LOGIN username password");
            return;
        }

        String username = tokens[1];
        String password = tokens[2];

        UsuarioEntity usuario = usuarioService.getUsuarioByUsername(username);
        if (usuario != null && usuario.getPassword().equals(password)) {
            this.usuario = usuario;
            usuario.setConectado(true);
            usuarioService.updateUsuario(usuario);
            out.println("Login exitoso");
        } else {
            out.println("Login fallido");
        }
    }

    private void handleStartGame(String[] tokens, PrintWriter out) {
        if (tokens.length != 2) {
            out.println("Uso: START_GAME opponent_username");
            return;
        }

        if (usuario == null) {
            out.println("Debe iniciar sesión primero");
            return;
        }

        String opponentUsername = tokens[1];
        UsuarioEntity opponent = usuarioService.getUsuarioByUsername(opponentUsername);
        if (opponent != null && opponent.isConectado()) {
            gameService.iniciarNuevaPartida(usuario.getId(), opponent.getId());
            out.println("Partida iniciada con " + opponentUsername);
        } else {
            out.println("El oponente no está disponible");
        }
    }

    private void handleMakeMove(String[] tokens, PrintWriter out) {
        if (tokens.length != 4) {
            out.println("Uso: MAKE_MOVE partida_id columna fila");
            return;
        }

        if (usuario == null) {
            out.println("Debe iniciar sesión primero");
            return;
        }

        int partidaId = Integer.parseInt(tokens[1]);
        char columna = tokens[2].charAt(0);
        int fila = Integer.parseInt(tokens[3]);

        gameService.realizarMovimiento(partidaId, usuario.getId(), columna, fila);
        out.println("Movimiento realizado");
    }

    private void handleLogout(PrintWriter out) {
        if (usuario != null) {
            usuario.setConectado(false);
            usuarioService.updateUsuario(usuario);
            out.println("Logout exitoso");
        } else {
            out.println("Debe iniciar sesión primero");
        }
    }
}