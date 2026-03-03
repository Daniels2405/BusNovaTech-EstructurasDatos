/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.busnovatech.Estructuras;

import com.google.gson.Gson;
import com.mycompany.busnovatech.Estructuras.ColaTickets;
import com.mycompany.busnovatech.Modelos.Tickets;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author daniel-2405
 */
public class ManagerTickets {

    private static final String RUTA = "data/tickets.json";
    private static final Gson gson = new Gson();

    // Guardar la cola de tickets en JSON
    public void guardarTickets(ColaTickets cola) throws Exception {
        File file = new File(RUTA);
        File parent = file.getParentFile();

        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        // Recorremos la cola para armar un arreglo temporal y serializar
        // Primero contamos cuántos hay
        int count = 0;
        ColaTickets copia = new ColaTickets();
        ColaTickets aux = new ColaTickets();

        // Vaciamos la cola original para contar, guardando en aux
        while (!cola.estaVacia()) {
            Tickets t = cola.desencolar();
            aux.encolar(t);
            count++;
        }

        // Restauramos la cola original y llenamos array
        Tickets[] arreglo = new Tickets[count];
        int i = 0;
        while (!aux.estaVacia()) {
            Tickets t = aux.desencolar();
            cola.encolar(t);   // restaurar
            arreglo[i++] = t;
        }

        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(arreglo, writer);
        }
    }

    // Cargar tickets desde JSON hacia una ColaTickets
    public ColaTickets cargarTickets() {
        ColaTickets cola = new ColaTickets();

        try (FileReader reader = new FileReader(RUTA)) {
            Tickets[] tickets = gson.fromJson(reader, Tickets[].class);
            if (tickets != null) {
                for (int i = 0; i < tickets.length; i++) {
                    cola.encolar(tickets[i]);
                }
            }
        } catch (Exception e) {
            // Si no existe el archivo, retorna cola vacía
        }

        return cola;
    }
}
