package com.mycompany.busnovatech;

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

        int opcion;

        do {
            String input = JOptionPane.showInputDialog(
                    "1. Registrar\n2. Iniciar sesión\n3. Salir"
            );

            if (input == null) { // si le da cancelar
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
            menuPrincipal(); // SOLO si login es correcto
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

        int opcionMenu;

        do {
            String texto = "========= BusNovaTech =========\n"
                    + "1. Módulo Buses\n"
                    + "2. Módulo Terminales\n"
                    + "3. Módulo Atención / Cola\n"
                    + "4. MÓDULO 4 (Reportes / Historial)\n"
                    + "5. Salir\n\n"
                    + "Digite una opción:";

            String input = JOptionPane.showInputDialog(texto);
            if (input == null) return;

            opcionMenu = Integer.parseInt(input);

            switch (opcionMenu) {

                case 1:
                    JOptionPane.showMessageDialog(null, "Módulo Buses (en desarrollo)");
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

        } while (opcionMenu != 5);
    }
}