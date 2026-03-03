/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.busnovatech.Estructuras;
import com.mycompany.busnovatech.Buses.*;

/**
 *
 * @author daniel-2405
 */
public class ColaBuses {
    private Bus frente;
    private Bus ultimo;
    public ColaBuses() {
        this.frente = null;
        this.ultimo = null;
    }
    public void Enqueue(int idBus, String terminal, TipoBus tipoBus){
        Bus nuevoBus = new Bus(idBus, terminal, tipoBus, 25);
        if (ultimo != null) {
            ultimo.setSiguiente(nuevoBus);
        }
        ultimo = nuevoBus;
        if (frente == null) {
            frente = nuevoBus;
        }
    }

    public Bus Dequeue() throws Exception{
        if (frente == null) {
            throw new Exception("La cola esta Vacia");
        }
        Bus busEliminado = frente;
        frente = frente.getSiguiente();
        if (frente == null) {
            ultimo = null;
        }
        return busEliminado;
    }

    public Bus front() throws Exception{
        if (frente == null) {
            throw new Exception("La cola esta Vacia");
        }
        return frente;
    }

    public boolean isEmpty(){
        return frente == null;
    }
}
