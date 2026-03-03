/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.busnovatech;

/**
 *
 * @author Mario
 */
import javax.swing.JOptionPane;

public class Registro {

    public static void registrar(ListaUsuarios lista, ManagerUsuarios manager) {

        String nombre = JOptionPane.showInputDialog("Ingrese nombre de usuario:");

        if (nombre == null || nombre.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nombre inválido.");
            return;
        }

        // Verificar si ya existe
        if (lista.buscarUsuario(nombre) != null) {
            JOptionPane.showMessageDialog(null, "El usuario ya existe.");
            return;
        }

        String contrasena;

        do {
            contrasena = JOptionPane.showInputDialog(
                    "Ingrese contraseña (Máximo 10 caracteres)");

            if (contrasena == null || contrasena.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Contraseña inválida.");
                return;
            }

            if (contrasena.length() > 10) {
                JOptionPane.showMessageDialog(null,
                        "Contraseña demasiado larga, máximo 10 caracteres.");
            }

        } while (contrasena.length() > 10);

        // Crear usuario
        Usuario nuevo = new Usuario(nombre, contrasena);

        // Agregar a lista enlazada
        lista.agregarUsuario(nuevo);

        try {
            manager.guardarUsuarios(lista);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error al guardar los usuarios.");
            return;
        }

        JOptionPane.showMessageDialog(null,
                "Usuario registrado correctamente.");
    }
}  
