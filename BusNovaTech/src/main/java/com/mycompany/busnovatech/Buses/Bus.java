/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.busnovatech.Buses;

/**
 *
 * @author daniel-2405
 */
public class Bus {
    private int idBus;
    private String terminal;
    private TipoBus tipoBus;
    private Bus siguiente;
    private int capacidad;

    public Bus(int idBus, String terminal, TipoBus tipoBus, int capacidad) {
        this.idBus = idBus;
        this.terminal = terminal;
        this.tipoBus = tipoBus;
        this.capacidad = capacidad; // Nota Mario: Añadí capacidad de ka clase bus que creó 
        //Jonh para evitar duplicaciones de clases pero manteniendo lo mejor de ambas.
        this.siguiente = null;
    }
    public int getIdBus() {
        return idBus;
    }
    public void setIdBus(int idBus) {
        this.idBus = idBus;
    }
    public String getTerminal() {
        return terminal;
    }
    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }
    public TipoBus getTipoBus() {
        return tipoBus;
    }
    public void setTipoBus(TipoBus tipoBus) {
        this.tipoBus = tipoBus;
    }
    public Bus getSiguiente() {
        return siguiente;
    }
    public void setSiguiente(Bus siguiente) {
        this.siguiente = siguiente;
    }
    public int getCapacidad() {
        return capacidad;
    }

    @Override
    public String toString() {
        return "Bus [idBus=" + idBus + ", terminal=" + terminal + ", tipoBus=" + tipoBus + ", capacidad=" + capacidad + "]";
    }
    
}

