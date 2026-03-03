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

    public Configuracion cargarConfig()throws Exception{
        File file = new File(RUTA_CONFIG);
        Configuracion config;

        // Si no existe el archivo, crear configuración inicial
        if (!file.exists()) {
            config = crearConfigInicial();
            guardarConfiguracion(config);
        } else {
            try (FileReader reader = new FileReader(file)) {
                config = gsonConfig.fromJson(reader, Configuracion.class);
            } catch (Exception e) {
                System.out.println("Configuración inválida, se recreará.");
                config = crearConfigInicial();
                guardarConfiguracion(config);
            }
        }

        int cantidad = solicitarCantidadBuses();

        ColaBuses nuevaCola = crearColaBusesDelDia(
                cantidad,
                config.getTerminalBuses().getNombreDeTerminal()
        );

        config.getTerminalBuses().setColaBuses(nuevaCola);

        guardarConfiguracion(config);

        return config;
    }
    
    private Configuracion crearConfigInicial() throws Exception{
        // Pedir al usuario los datos de la terminal
        String nombre = JOptionPane.showInputDialog(
                null,
                "Ingrese el nombre de la terminal:",
                "Crear Terminal",
                JOptionPane.QUESTION_MESSAGE
        );

        if (nombre == null || nombre.isEmpty()) {
            throw new Exception("Nombre de terminal inválido o cancelado.");
        }

        String direccion = JOptionPane.showInputDialog(
                null,
                "Ingrese la dirección de la terminal:",
                "Crear Terminal",
                JOptionPane.QUESTION_MESSAGE
        );

        if (direccion == null) {
            direccion = "";
        }

        TerminalDeBuses terminal = new TerminalDeBuses();
        terminal.setNombreDeTerminal(nombre);
        terminal.setDireccionDeTerminal(direccion);

        Configuracion config = new Configuracion();
        config.setTerminalBuses(terminal);

        return config;
    }

    // Método separado para solicitar la cantidad de buses al usuario
    private int solicitarCantidadBuses() throws Exception {
        while (true) {

            String entrada = JOptionPane.showInputDialog(
                    null,
                    "Ingrese la cantidad total de buses del día (mínimo 3):",
                    "Cantidad de Buses",
                    JOptionPane.QUESTION_MESSAGE
            );

            if (entrada == null) {
                throw new Exception("Ingreso cancelado por el usuario.");
            }

            try {
                int cantidad = Integer.parseInt(entrada);

                if (cantidad < 3) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Debe ingresar mínimo 3 buses (1 Directo, 1 Preferencial y 1 Normal).",
                            "Entrada inválida",
                            JOptionPane.WARNING_MESSAGE
                    );
                    continue;
                }

                return cantidad;

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(
                        null,
                        "Ingrese un número válido.",
                        "Entrada inválida",
                        JOptionPane.WARNING_MESSAGE
                );
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

            System.out.println("Archivo guardado en: " + file.getAbsolutePath());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al guardar la configuración",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private ColaBuses crearColaBusesDelDia(int cantidad, String nombreTerminal) {

    ColaBuses nuevaCola = new ColaBuses();

    for (int id = 1; id <= cantidad; id++) {
        if (id == 1) {
            nuevaCola.Enqueue(id, nombreTerminal, TipoBus.Directo);
        } 
        else if (id == 2) {
            nuevaCola.Enqueue(id, nombreTerminal, TipoBus.Preferencial);
        } 
        else {
            nuevaCola.Enqueue(id, nombreTerminal, TipoBus.Regular);
        }
    }

    return nuevaCola;
}

    public static Gson getGsonconfig() {
        return gsonConfig;
    }
}
