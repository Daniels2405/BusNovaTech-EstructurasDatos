/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.busnovatech;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ManagerUsuarios {

    private static final String RUTA = "data/usuarios.json";
    private static final Gson gson = new Gson();

    // Convertir lista enlazada → ArrayList → JSON
    public void guardarUsuarios(ListaUsuarios lista) throws Exception {

        List<Usuario> usuarios = lista.toArrayList();

        File file = new File(RUTA);
        File parent = file.getParentFile();

        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(usuarios, writer);
        }
    }

    // Leer JSON → ArrayList
    public List<Usuario> cargarUsuarios() {

        try (FileReader reader = new FileReader(RUTA)) {

            Type tipoLista = new TypeToken<List<Usuario>>() {}.getType();
            return gson.fromJson(reader, tipoLista);

        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}