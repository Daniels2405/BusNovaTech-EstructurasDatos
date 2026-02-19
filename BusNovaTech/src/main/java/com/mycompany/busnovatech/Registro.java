/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.busnovatech;

/**
 *
 * @author Mario
 */import javax.swing.JOptionPane;
import java.io.FileWriter;
import java.io.IOException;

public class Registro {

    public static void registrar(ListaUsuarios lista) {

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

        String contrasena = JOptionPane.showInputDialog("Ingrese contraseña:");

        if (contrasena == null || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Contraseña inválida.");
            return;
        }

        Usuario nuevo = new Usuario(nombre, contrasena);

        // Agregar a la lista
        lista.agregarUsuario(nuevo);

        // Guardar en archivo
        try (FileWriter fw = new FileWriter("usuarios.txt", true)) {
            fw.write(nuevo.toArchivo() + "\n");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar usuario.");
            return;
        }

        JOptionPane.showMessageDialog(null, "Usuario registrado correctamente.");
    }
}
