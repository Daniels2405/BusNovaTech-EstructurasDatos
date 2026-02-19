/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.busnovatech;
import javax.swing.JOptionPane;

import com.mycompany.busnovatech.config.*;
import com.mycompany.busnovatech.Buses.*;
import com.mycompany.busnovatech.Estructuras.*;

/**
 *
 * 
 */
public class Main {
public static ColaBuses colaBuses;
public static ConfigManager configManager = new ConfigManager();
public static Configuracion configuracion;
    public static void main(String[] args) {
        menuPrincipal();
    }

    public static void menuPrincipal(){
        try {
            configuracion = configManager.cargarConfig(null);
            // Si la configuración contiene una terminal con cola, inicializar la referencia local
            if (configuracion != null && configuracion.getTerminalBuses() != null) {
                colaBuses = configuracion.getTerminalBuses().getColaBuses();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar/crear la configuración: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        String menu = "========= BusNovaTech =========\n" +
                        "Funcionad en Desarrollo";
        JOptionPane.showMessageDialog(null, menu, "BusNovaTech", JOptionPane.INFORMATION_MESSAGE);
    }
}
