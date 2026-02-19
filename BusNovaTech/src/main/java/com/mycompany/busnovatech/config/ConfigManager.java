/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.busnovatech.config;
import com.mycompany.busnovatech.Buses.*;
import com.mycompany.busnovatech.Estructuras.*;
import com.mycompany.busnovatech.Terminales.TerminalDeBuses;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import com.google.gson.Gson;
import javax.swing.JOptionPane;

/**
 *
 * @author daniel-2405
 */
public class ConfigManager {
    //Se configura la ruta donde se guardara el json y se crea un objeto tipo Gson
    private static final String RUTA_CONFIG = "data/config.json";
    private static final Gson gsonConfig = new Gson();

    public Configuracion cargarConfig(ColaBuses colaBuses)throws Exception{
        File file = new File(RUTA_CONFIG);
        if (!file.exists()){
            try {
                Configuracion config = crearConfigInicial(colaBuses);
                guardarConfiguracion(config);
                return config;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error al crear la configuración", JOptionPane.ERROR_MESSAGE);
            }
        }
        try (FileReader reader = new FileReader(file)){
            return gsonConfig.fromJson(reader, Configuracion.class);
        } catch (Exception e) {
            System.out.println("Configuración inválida, se recreará.");
            Configuracion config = crearConfigInicial(colaBuses);
            guardarConfiguracion(config);
            return config;
        }
    }
    
    private Configuracion crearConfigInicial(ColaBuses colaBuses) throws Exception{
        // Pedir al usuario los datos de la terminal
        String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre de la terminal:", "Crear Terminal", JOptionPane.QUESTION_MESSAGE);
        if (nombre == null || nombre.isEmpty()) {
            throw new Exception("Nombre de terminal inválido o cancelado.");
        }
        String direccion = JOptionPane.showInputDialog(null, "Ingrese la dirección de la terminal:", "Crear Terminal", JOptionPane.QUESTION_MESSAGE);
        if (direccion == null) {
            direccion = "";
        }

        // Solicitar cantidad de buses mediante método separado
        int cantidad = solicitarCantidadBuses();

        // Crear la terminal y la cola de buses
        TerminalDeBuses terminal = new TerminalDeBuses();
        terminal.setNombreDeTerminal(nombre);
        terminal.setDireccionDeTerminal(direccion);

        ColaBuses nuevaCola = new ColaBuses();
        int id = 1;
        while (id <= cantidad) {
            nuevaCola.Enqueue(id, nombre, TipoBus.Regular);
            id++;
        }
        terminal.setColaBuses(nuevaCola);

        Configuracion config = new Configuracion();
        config.setTerminalBuses(terminal);
        return config;
    }

    // Método separado para solicitar la cantidad de buses al usuario
    private int solicitarCantidadBuses() throws Exception {
        while (true) {
            String entrada = JOptionPane.showInputDialog(null, "Ingrese la cantidad total de buses:", "Cantidad de Buses", JOptionPane.QUESTION_MESSAGE);
            if (entrada == null) {
                throw new Exception("Ingreso de cantidad cancelado por el usuario.");
            }
            try {
                int cantidad = Integer.parseInt(entrada);
                if (cantidad < 0) {
                    JOptionPane.showMessageDialog(null, "Ingrese un número mayor o igual a 0.", "Entrada inválida", JOptionPane.WARNING_MESSAGE);
                    continue;
                }
                return cantidad;
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Por favor ingrese un número válido.", "Entrada inválida", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    
    public void guardarConfiguracion(Configuracion config) throws Exception{
        try {
            File file = new File(RUTA_CONFIG);
            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            try (FileWriter writer = new FileWriter(file)) {
                gsonConfig.toJson(config, writer);
            }
            System.out.println("Archivo Creado: " + file.getAbsolutePath());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al guardar la configuracion", "Error" + RUTA_CONFIG, JOptionPane.ERROR_MESSAGE);
        }
    }

    public static Gson getGsonconfig() {
        return gsonConfig;
    }
}
