package com.mycompany.hundirlaflotacliente;

import java.io.IOException;

public class Autenticacion {
    private Cliente cliente;

    public Autenticacion(Cliente cliente) {
        this.cliente = cliente;
    }

    public boolean login(String username, String password) throws IOException {
        String response = cliente.sendMessage("LOGIN " + username + " " + password);
        return "OK".equals(response);
    }
}