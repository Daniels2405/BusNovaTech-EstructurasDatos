/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.busnovatech;

/**
 *
 * @author Grupo 1
 */

import javax.swing.JOptionPane;

import com.mycompany.busnovatech.config.*;
import com.mycompany.busnovatech.Buses.*;
import com.mycompany.busnovatech.Estructuras.*;

public class Main {
    
    public static ColaBuses colaBuses;
    public static ConfigManager configManager = new ConfigManager();
    public static Configuracion configuracion;

    public static ListaUsuarios listaUsuarios;
    public static ManagerUsuarios managerUsuarios;

    public static void main(String[] args) {
        
        managerUsuarios = new ManagerUsuarios();
        listaUsuarios = new ListaUsuarios();

        // Cargar usuarios desde JSON al iniciar
        for (Usuario u : managerUsuarios.cargarUsuarios()) {
            listaUsuarios.agregarUsuario(u);
        }

       
            int opcion = 0;

        do { 
            try {

                String input = JOptionPane.showInputDialog(
                        "1. Registrar\n2. Iniciar sesión\n3. Salir"
                );

                if (input == null) { // si presiona cancelar
                    opcion = 3;
                } else {
                    opcion = Integer.parseInt(input);
                }

                switch (opcion) {

                    case 1:
                        Registro.registrar(listaUsuarios, managerUsuarios);
                        break;

                    case 2:
                        login(listaUsuarios);
                        break;

                    case 3:
                        JOptionPane.showMessageDialog(null, "Saliendo...");
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "Opción inválida");
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, 
                    "Error: Debe ingresar un número válido.");
            }
            } while (opcion != 3);
        }
    
    

    public static void login(ListaUsuarios lista) {

        String nombre = JOptionPane.showInputDialog("Usuario:");
        if (nombre == null) return;

        String pass = JOptionPane.showInputDialog("Contraseña:");
        if (pass == null) return;

        Usuario usuario = lista.buscarUsuario(nombre);

        if (usuario != null && usuario.validarContrasena(pass)) {
            JOptionPane.showMessageDialog(null, "Bienvenido " + nombre);
            menuPrincipal(); // Solo si login es correcto
        } else {
            JOptionPane.showMessageDialog(null, "Credenciales incorrectas.");
        }
    }

public static void menuPrincipal() {

    try {
        configuracion = configManager.cargarConfig(null);

        if (configuracion != null && configuracion.getTerminalBuses() != null) {
            colaBuses = configuracion.getTerminalBuses().getColaBuses();
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null,
                "Error al cargar/crear la configuración: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    int opcionMenu = 0;

    do {
        try {
            String texto = "========= BusNovaTech =========\n"
                    + "1. Módulo Buses\n"
                    + "2. Módulo Terminales\n"
                    + "3. Módulo Atención / Cola\n"
                    + "4. MÓDULO 4 (Reportes / Historial)\n"
                    + "5. Cerrar Sesión\n\n"
                    + "Digite una opción:";

            String input = JOptionPane.showInputDialog(texto);
            if (input == null) return;

            opcionMenu = Integer.parseInt(input);

            switch (opcionMenu) {

            case 1:
               // Crear terminal si no existe
               if (configuracion == null || configuracion.getTerminalBuses() == null) {
                   configuracion = configManager.crearTerminal(); // Método público en ConfigManager
                   colaBuses = configuracion.getTerminalBuses().getColaBuses();
                   JOptionPane.showMessageDialog(null, "Terminal y buses creados correctamente.");
               }

               // Mostrar lista de buses
               if (colaBuses != null && !colaBuses.isEmpty()) {
                   String mensaje = "=== Lista de Buses ===\n";
                   Bus actual = colaBuses.front(); // front() devuelve el primer Bus
                   while (actual != null) {
                       mensaje += "ID: " + actual.getIdBus() +
                                  ", Terminal: " + actual.getTerminal() +
                                  ", Tipo: " + actual.getTipoBus() + "\n";
                       actual = actual.getSiguiente(); // Siguiente bus en la cola
                   }
                   JOptionPane.showMessageDialog(null, mensaje);
               } else {
                   JOptionPane.showMessageDialog(null, "No hay buses disponibles.");
               }
               break;

                case 2:
                    JOptionPane.showMessageDialog(null, "Módulo Terminales (en desarrollo)");
                    break;

                case 3:
                    JOptionPane.showMessageDialog(null, "Módulo Atención / Cola (en desarrollo)");
                    break;

                case 4:
                    Modulo4Aportes.mostrar(listaUsuarios, configuracion);
                    break;

                case 5:
                    JOptionPane.showMessageDialog(null, "Saliendo del menú...");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Debe ingresar un número válido.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inesperado: " + e.getMessage());
        }

    } while (opcionMenu != 5);
}
}
