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
public class NodoTickets {
    private Tickets dato;
    private NodoTickets siguiente;
    
    public NodoTickets(Tickets dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public Tickets getDato() {
        return dato;
    }

    public void setDato(Tickets dato) {
        this.dato = dato;
    }

    public NodoTickets getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoTickets siguiente) {
        this.siguiente = siguiente;
    }
    
}
