/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.busnovatech;

import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {

        ManagerUsuarios manager = new ManagerUsuarios();
        ListaUsuarios lista = new ListaUsuarios();

        // Cargar usuarios desde JSON al iniciar
        for (Usuario u : manager.cargarUsuarios()) {
            lista.agregarUsuario(u);
        }

        int opcion;

        do {
            opcion = Integer.parseInt(
                JOptionPane.showInputDialog(
                    "1. Registrar\n2. Iniciar sesión\n3. Salir"
                )
            );

            switch (opcion) {

                case 1:
                    Registro.registrar(lista, manager);
                    break;

                case 2:
                    login(lista);
                    break;

                case 3:
                    JOptionPane.showMessageDialog(null, "Saliendo...");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida");
            }

        } while (opcion != 3);
    }

    public static void login(ListaUsuarios lista) {

        String nombre = JOptionPane.showInputDialog("Usuario:");
        String pass = JOptionPane.showInputDialog("Contraseña:");

        Usuario usuario = lista.buscarUsuario(nombre);

        if (usuario != null && usuario.validarContrasena(pass)) {
            JOptionPane.showMessageDialog(null, "Bienvenido " + nombre);
        } else {
            JOptionPane.showMessageDialog(null, "Credenciales incorrectas.");
        }
    }
}


