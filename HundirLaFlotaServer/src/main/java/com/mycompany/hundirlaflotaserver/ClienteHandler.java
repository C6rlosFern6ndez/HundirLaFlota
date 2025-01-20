package com.mycompany.hundirlaflotaserver;

import com.mycompany.hundirlaflotaserver.service.GameService;
import com.mycompany.hundirlaflotaserver.service.UsuarioService;
import com.mycompany.hundirlaflotaserver.service.PartidaService;
import com.mycompany.hundirlaflotaserver.PartidaEntity;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class ClienteHandler extends Thread {
    private Socket clientSocket;
    private GameService gameService;
    private UsuarioService usuarioService;
    private PartidaService partidaService;
    private UsuarioEntity usuario;

    public ClienteHandler(Socket socket, GameService gameService) {
        this.clientSocket = socket;
        this.gameService = gameService;
        this.usuarioService = new UsuarioService();
        this.partidaService = new PartidaService();
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
                    case "VIEW_FINISHED_GAMES":
                        handleViewFinishedGames(out);
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
            out.println("LOGIN_SUCCESS");
        } else {
            out.println("LOGIN_FAILED");
        }
    }

    private void handleStartGame(String[] tokens, PrintWriter out) {
        if (tokens.length != 3) {
            out.println("Uso: START_GAME jugador1 jugador2");
            return;
        }

        String jugador1 = tokens[1];
        String jugador2 = tokens[2];

        UsuarioEntity usuario1 = usuarioService.getUsuarioByUsername(jugador1);
        UsuarioEntity usuario2 = usuarioService.getUsuarioByUsername(jugador2);

        if (usuario1 != null && usuario2 != null) {
            gameService.iniciarNuevaPartida(usuario1.getId(), usuario2.getId());
            out.println("Partida iniciada entre " + jugador1 + " y " + jugador2);
        } else {
            out.println("Uno o ambos jugadores no existen");
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

    private void handleViewFinishedGames(PrintWriter out) {
        List<PartidaEntity> partidasTerminadas = partidaService.getPartidasTerminadas();
        if (partidasTerminadas != null && !partidasTerminadas.isEmpty()) {
            StringBuilder response = new StringBuilder("FINISHED_GAMES\n");
            for (PartidaEntity partida : partidasTerminadas) {
                response.append("partida_id: ").append(partida.getId())
                        .append(", jugador1: ").append(partida.getJugador1().getUsername())
                        .append(", jugador2: ").append(partida.getJugador2().getUsername())
                        .append(", estado: ").append(partida.getEstado())
                        .append(", turno: ").append(partida.getTurno())
                        .append(", fecha_inicio: ").append(partida.getFechaInicio())
                        .append(", fecha_fin: ").append(partida.getFechaFin())
                        .append("\n");
            }
            out.println(response.toString());
        } else {
            out.println("FINISHED_GAMES No hay partidas terminadas.");
        }
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