/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.busnovatech;
import com.mycompany.busnovatech.config.*;

/**
 *
 * 
 */
public class Main {

    public static void main(String[] args) {
        pruebas();
    }
    public static void pruebas(){
        ConfiManager configuracion = new ConfiManager();
        configuracion.cargarConfig();
    }
}
