/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.busnovatech;

/**
 *
 * @author Mario
 */
public class Usuario {

    // Atributos encapsulados
    private String nombreUsuario;
    private String contrasena;

    // Constructor
    public Usuario(String nombreUsuario, String contrasena) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
    }

    // Getter para el nombre de usuario
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    // Método para validar la contraseña
    public boolean validarContrasena(String contrasenaIngresada) {
        return this.contrasena.equals(contrasenaIngresada);
    }

    // Método para convertir el usuario en formato de archivo
    public String toArchivo() {
        return nombreUsuario + ";" + contrasena;
    }

    // Método estático para crear un usuario desde una línea del archivo
    public static Usuario desdeArchivo(String linea) {
        String[] partes = linea.split(";");
        return new Usuario(partes[0], partes[1]);
    }
}
