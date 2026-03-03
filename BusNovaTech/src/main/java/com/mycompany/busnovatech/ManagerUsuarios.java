package com.mycompany.busnovatech;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * Maneja la persistencia de usuarios en JSON
 * sin usar ArrayList ni List.
 */
public class ManagerUsuarios {

    private static final String RUTA = "data/usuarios.json";
    private static final Gson gson = new Gson();

    // Guardar lista enlazada manualmente
    public void guardarUsuarios(ListaUsuarios lista) throws Exception {

        File file = new File(RUTA);
        File parent = file.getParentFile();

        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        try (FileWriter writer = new FileWriter(file)) {

            writer.write("[");

            NodoUsuario actual = lista.getCabeza();
            boolean primero = true;

            while (actual != null) {

                if (!primero) {
                    writer.write(",");
                }

                writer.write(gson.toJson(actual.dato));

                primero = false;
                actual = actual.siguiente;
            }

            writer.write("]");
        }
    }

    // Cargar JSON a ListaUsuarios
    public ListaUsuarios cargarUsuarios() {

        ListaUsuarios lista = new ListaUsuarios();

        try (FileReader reader = new FileReader(RUTA)) {

            Usuario[] usuarios = gson.fromJson(reader, Usuario[].class);

            if (usuarios != null) {
                for (int i = 0; i < usuarios.length; i++) {
                    lista.agregarUsuario(usuarios[i]);
                }
            }

        } catch (Exception e) {
            // Si no existe archivo, retorna lista vacía
        }

        return lista;
    }
}