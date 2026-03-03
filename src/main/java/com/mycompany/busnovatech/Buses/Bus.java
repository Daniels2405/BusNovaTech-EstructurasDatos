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

    public Bus(int idBus, String terminal, TipoBus tipoBus) {
        this.idBus = idBus;
        this.terminal = terminal;
        this.tipoBus = tipoBus;
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

    @Override
    public String toString() {
        return "Bus [idBus=" + idBus + ", terminal=" + terminal + ", tipoBus=" + tipoBus + "]";
    }
    
}
