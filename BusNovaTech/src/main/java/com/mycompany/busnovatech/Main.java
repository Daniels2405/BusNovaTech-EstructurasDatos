package com.mycompany.busnovatech;

import javax.swing.JOptionPane;

import com.mycompany.busnovatech.config.*;
import com.mycompany.busnovatech.Buses.*;
import com.mycompany.busnovatech.Estructuras.*;
import com.mycompany.busnovatech.Modelos.Tickets;

public class Main {
    public static ColaBuses colaBuses;
    public static ConfigManager configManager = new ConfigManager();
    public static Configuracion configuracion;

    public static ListaUsuarios listaUsuarios;
    public static ManagerUsuarios managerUsuarios;
    
    public static ColaTickets colaTickets;
    public static ManagerTickets managerTickets = new ManagerTickets();

    public static void main(String[] args) {
        
        managerUsuarios = new ManagerUsuarios();

        // CARGA CORREGIDA (sin List, sin for-each)
        listaUsuarios = managerUsuarios.cargarUsuarios();

        int opcion = 0;

        do { 
            try {

                String input = JOptionPane.showInputDialog(
                        "1. Registrar\n2. Iniciar sesión\n3. Salir"
                );

                if (input == null) {
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
            menuPrincipal();
        } else {
            JOptionPane.showMessageDialog(null, "Credenciales incorrectas.");
        }
    }

    public static void menuPrincipal() {

        try {
            configuracion = configManager.cargarConfig();

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
                        + "5. Tickets\n"
                        + "6. Cerrar Sesión\n\n"
                        + "Digite una opción:";

                String input = JOptionPane.showInputDialog(texto);
                if (input == null) return;

                opcionMenu = Integer.parseInt(input);

                switch (opcionMenu) {

                    case 1:
                        if (configuracion == null || configuracion.getTerminalBuses() == null) {
                            configuracion = configManager.crearTerminal();
                            colaBuses = configuracion.getTerminalBuses().getColaBuses();
                            JOptionPane.showMessageDialog(null, "Terminal y buses creados correctamente.");
                        }

                        if (colaBuses != null && !colaBuses.isEmpty()) {
                            String mensaje = "=== Lista de Buses ===\n";
                            Bus actual = colaBuses.front();
                            while (actual != null) {
                                mensaje += "ID: " + actual.getIdBus() +
                                           ", Terminal: " + actual.getTerminal() +
                                           ", Tipo: " + actual.getTipoBus() + "\n";
                                actual = actual.getSiguiente();
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
                        menuTickets();
                        break;

                    case 6:
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

        } while (opcionMenu != 6);
    }

    // ─── MÓDULO TICKETS ───────────────────────────────────────────────────────

    public static void menuTickets() {
        int opcion = 0;

        do {
            try {
                String input = JOptionPane.showInputDialog(
                        "======= Módulo Tickets =======\n"
                        + "1. Crear ticket\n"
                        + "2. Ver tickets guardados\n"
                        + "3. Volver\n\n"
                        + "Digite una opción:"
                );

                if (input == null) return;

                opcion = Integer.parseInt(input);

                switch (opcion) {
                    case 1:
                        crearTicket();
                        break;
                    case 2:
                        verTickets();
                        break;
                    case 3:
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opción inválida.");
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error: Debe ingresar un número válido.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error inesperado: " + e.getMessage());
            }

        } while (opcion != 3);
    }

    public static void crearTicket() {
        // Ruta
        String ruta = JOptionPane.showInputDialog("Ingrese la ruta del ticket\n(ej: San José - Cartago):");
        if (ruta == null || ruta.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ruta inválida.");
            return;
        }

        // Día
        String dia = JOptionPane.showInputDialog("Ingrese el día del viaje\n(ej: Lunes 12/05/2025):");
        if (dia == null || dia.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Día inválido.");
            return;
        }

        // Hora
        String hora = JOptionPane.showInputDialog("Ingrese la hora del viaje\n(ej: 08:30):");
        if (hora == null || hora.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Hora inválida.");
            return;
        }

        // Cantidad
        int cantidad = 0;
        try {
            String inputCantidad = JOptionPane.showInputDialog("Ingrese la cantidad de tickets:");
            if (inputCantidad == null) return;
            cantidad = Integer.parseInt(inputCantidad);
            if (cantidad <= 0) {
                JOptionPane.showMessageDialog(null, "La cantidad debe ser mayor a 0.");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Cantidad inválida.");
            return;
        }

        // Precio
        int precio = 0;
        try {
            String inputPrecio = JOptionPane.showInputDialog("Ingrese el precio por ticket (₡):");
            if (inputPrecio == null) return;
            precio = Integer.parseInt(inputPrecio);
            if (precio < 0) {
                JOptionPane.showMessageDialog(null, "El precio no puede ser negativo.");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Precio inválido.");
            return;
        }

        // Crear y encolar el ticket
        Tickets nuevoTicket = new Tickets(ruta, dia, hora, cantidad, precio);
        colaTickets.encolar(nuevoTicket);

        // Guardar en archivo
        try {
            managerTickets.guardarTickets(colaTickets);
            JOptionPane.showMessageDialog(null,
                    "✔ Ticket creado y guardado exitosamente.\n\n"
                    + "Ruta: " + ruta + "\n"
                    + "Día: " + dia + "\n"
                    + "Hora: " + hora + "\n"
                    + "Cantidad: " + cantidad + "\n"
                    + "Precio: ₡" + precio);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el ticket: " + e.getMessage());
        }
    }

    public static void verTickets() throws Exception {
        if (colaTickets.estaVacia()) {
            JOptionPane.showMessageDialog(null, "No hay tickets registrados.");
            return;
        }

        // Recorremos sin destruir la cola: vaciamos, mostramos y restauramos
        String mensaje = "======= Tickets Registrados =======\n\n";
        ColaTickets aux = new ColaTickets();
        int numero = 1;

        while (!colaTickets.estaVacia()) {
            Tickets t = colaTickets.desencolar();
            mensaje += "Ticket #" + numero + "\n"
                     + "  Ruta:     " + t.getRuta() + "\n"
                     + "  Día:      " + t.getDia() + "\n"
                     + "  Hora:     " + t.getHora() + "\n"
                     + "  Cantidad: " + t.getCantidad() + "\n"
                     + "  Precio:   ₡" + t.getPrecio() + "\n\n";
            aux.encolar(t);
            numero++;
        }

        // Restaurar la cola original
        while (!aux.estaVacia()) {
            colaTickets.encolar(aux.desencolar());
        }

        JOptionPane.showMessageDialog(null, mensaje);
    }
}