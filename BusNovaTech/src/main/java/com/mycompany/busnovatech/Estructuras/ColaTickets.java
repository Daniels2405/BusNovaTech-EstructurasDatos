/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.busnovatech.Estructuras;

import com.mycompany.busnovatech.Modelos.Tickets;

/**
 *
 * @author Admin_User
 */
public class ColaTickets {
    private NodoTickets frente;
    private NodoTickets fin;
    
    public ColaTickets() {
        this.frente = null;
        this.fin = null;
    }
    
    public void encolar(Tickets dato) {
        NodoTickets nuevo = new NodoTickets(dato);
        if (fin != null) {
            fin.setSiguiente(nuevo);
        }
        fin = nuevo;
        if (frente == null) {
            frente = nuevo;
        }
    }
    
    public Tickets desencolar() throws Exception {
        if (frente == null) throw new Exception("La cola esta vacia");
        Tickets dato = frente.getDato();   
        frente = frente.getSiguiente();
        if (frente == null) fin = null;
        return dato;

    }
    
    public Tickets frente() throws Exception {
        if (frente == null) throw new Exception("La cola esta vacia");
        return frente.getDato();
    }
    
    public boolean estaVacia() {
        return frente == null;
    }
}
