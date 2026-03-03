/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.busnovatech.Modelos;

import com.mycompany.busnovatech.Buses.TipoBus;

/**
 *
 * @author Admin_User
 */
public class Bus {
    private int capacidad;
    private TipoBus tipoBus;

    public Bus(int capacidad, TipoBus tipoBus) {
        this.capacidad = capacidad;
        this.tipoBus = tipoBus;
    }
    public int getCapacidad() {
        return capacidad;
    }
    public TipoBus getTipoBus() {
        return tipoBus;
    }
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
    public void setTipoBus(TipoBus tipoBus) {
        this.tipoBus = tipoBus;
    }

}