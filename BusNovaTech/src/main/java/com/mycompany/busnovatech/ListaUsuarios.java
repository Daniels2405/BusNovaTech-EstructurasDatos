/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.busnovatech;

/**
 *
 * @author Mario
 */
public class ListaUsuarios {

    private NodoUsuario cabeza;

    public ListaUsuarios() {
        cabeza = null;
    }

    // Agregar usuario al final
    public void agregarUsuario(Usuario usuario) {

        NodoUsuario nuevo = new NodoUsuario(usuario);

        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            NodoUsuario actual = cabeza;

            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }

            actual.siguiente = nuevo;
        }
    }

    // Buscar usuario por nombre
    public Usuario buscarUsuario(String nombre) {

        NodoUsuario actual = cabeza;

        while (actual != null) {

            if (actual.dato.getNombreUsuario().equals(nombre)) {
                return actual.dato;
            }

            actual = actual.siguiente;
        }

        return null; // No encontrado
    }
}
