/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.busnovatech;
import javax.swing.JOptionPane;

import com.mycompany.busnovatech.config.*;
import com.mycompany.busnovatech.Buses.*;
import com.mycompany.busnovatech.Estructuras.*;

import javax.swing.JOptionPane;

public class Main {
public static ColaBuses colaBuses;
public static ConfigManager configManager = new ConfigManager();
public static Configuracion configuracion;
    public static void main(String[] args) {

        ManagerUsuarios manager = new ManagerUsuarios();
        ListaUsuarios lista = new ListaUsuarios();

        // Cargar usuarios desde JSON al iniciar
        for (Usuario u : manager.cargarUsuarios()) {
            lista.agregarUsuario(u);
        }

        int opcion;

        do {
            opcion = Integer.parseInt(
                JOptionPane.showInputDialog(
                    "1. Registrar\n2. Iniciar sesión\n3. Salir"
                )
            );

            switch (opcion) {

                case 1:
                    Registro.registrar(lista, manager);
                    break;

                case 2:
                    login(lista);
                    break;

                case 3:
                    JOptionPane.showMessageDialog(null, "Saliendo...");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida");
            }

        } while (opcion != 3);
    }

    public static void login(ListaUsuarios lista) {

        String nombre = JOptionPane.showInputDialog("Usuario:");
        String pass = JOptionPane.showInputDialog("Contraseña:");

        Usuario usuario = lista.buscarUsuario(nombre);

        if (usuario != null && usuario.validarContrasena(pass)) {
            JOptionPane.showMessageDialog(null, "Bienvenido " + nombre);
            menuPrincipal();   
        } else {
            JOptionPane.showMessageDialog(null, "Credenciales incorrectas.");
        }
        
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


