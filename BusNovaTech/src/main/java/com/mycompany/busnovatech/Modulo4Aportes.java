/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.busnovatech;

/**
 *
 * @author duart
 */


import javax.swing.JOptionPane;
import com.mycompany.busnovatech.config.Configuracion;

public class Modulo4Aportes {

    public static void mostrar(ListaUsuarios listaUsuarios, Configuracion configuracion) {

        String mensaje = "=== LISTA DE USUARIOS ===\n\n";

        NodoUsuario actual = listaUsuarios.getCabeza();

        if (actual == null) {
            mensaje += "No hay usuarios registrados.\n";
        } else {
            while (actual != null) {
                mensaje += "- " + actual.dato.getNombreUsuario() + "\n";
                actual = actual.siguiente;
            }
        }

        JOptionPane.showMessageDialog(null, mensaje);
    }
}