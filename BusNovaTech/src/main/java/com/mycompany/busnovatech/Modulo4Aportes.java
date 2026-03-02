/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.busnovatech;

/**
 *
 * @author duart
 */


import javax.swing.JOptionPane;
import com.mycompany.busnovatech.config.Configuracion;

public class Modulo4Aportes {

    public static void mostrar(ListaUsuarios listaUsuarios, Configuracion configuracion) {

        int op = 0;

        do {
            String menu = "===== MÓDULO 4: Reportes / Historial =====\n"
                    + "1) Cantidad de usuarios registrados\n"
                    + "2) Listar usuarios\n"
                    + "3) Volver\n";

            op = Integer.parseInt(JOptionPane.showInputDialog(menu));

            switch (op) {

                case 1:
                    int total = 0;

                    if (listaUsuarios != null) {
                        total = listaUsuarios.toArrayList().size();
                    }

                    JOptionPane.showMessageDialog(null,
                            "Usuarios registrados: " + total,
                            "Reporte",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;

                case 2:
                    JOptionPane.showMessageDialog(null,
                            listarUsuarios(listaUsuarios),
                            "Listado de usuarios",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;

                case 3:
                    // volver al menú principal
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida");
            }

        } while (op != 3);
    }

    private static String listarUsuarios(ListaUsuarios listaUsuarios) {

        if (listaUsuarios == null) {
            return "No hay usuarios.";
        }

        String texto = "===== LISTADO DE USUARIOS =====\n";

        java.util.List<Usuario> usuarios = listaUsuarios.toArrayList();

        if (usuarios.size() == 0) {
            return "No hay usuarios registrados.";
        }

        for (Usuario u : usuarios) {
            texto += "- " + u.getNombreUsuario() + "\n";
        }

        return texto;
    }
}