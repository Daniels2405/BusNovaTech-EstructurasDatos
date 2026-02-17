/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.busnovatech.config;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import com.google.gson.Gson;
import javax.swing.JOptionPane;

/**
 *
 * @author daniel-2405
 */
public class ConfiManager {
    //Se configura la ruta donde se guardara el json y se crea un objeto tipo Gson
    private static final String RUTA_CONFIG = "data/config.json";
    private static final Gson gsonConfig = new Gson();

    public Configuracion cargarConfig(){
        File file = new File(RUTA_CONFIG);
        if (!file.exists()){
            Configuracion config = crearConfigInicial();
            guardarConfiguracion(config);
            return config;
        }
        try (FileReader reader = new FileReader(file)){
            return gsonConfig.fromJson(reader, Configuracion.class);
        } catch (Exception e) {
            System.out.println("Configuración inválida, se recreará.");
            Configuracion config = crearConfigInicial();
            return config;
        }
    }

    private void guardarConfiguracion(Configuracion config){
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

    private Configuracion crearConfigInicial(){
        Configuracion config = new Configuracion();
        return config;
    }

    public static Gson getGsonconfig() {
        return gsonConfig;
    }
}
