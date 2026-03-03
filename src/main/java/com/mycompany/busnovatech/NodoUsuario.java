/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.busnovatech;

/**
 *
 * @author Mario
 */
public class NodoUsuario {


    Usuario dato;
    NodoUsuario siguiente;
        
    
    public NodoUsuario(Usuario dato) {
        this.dato = dato;
        this.siguiente = null;
    }
    
}
