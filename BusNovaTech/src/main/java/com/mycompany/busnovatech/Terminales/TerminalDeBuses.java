/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.busnovatech.Terminales;

import com.mycompany.busnovatech.Estructuras.ColaBuses;

/**
 *
 * @author daniel-2405
 */
public class TerminalDeBuses {
    private String nombreDeTerminal;
    private String direccionDeTerminal;
    private ColaBuses colaBuses;

    public String getNombreDeTerminal() {
        return nombreDeTerminal;
    }

    public void setNombreDeTerminal(String nombreDeTerminal) {
        this.nombreDeTerminal = nombreDeTerminal;
    }

    public String getDireccionDeTerminal() {
        return direccionDeTerminal;
    }

    public void setDireccionDeTerminal(String direccionDeTerminal) {
        this.direccionDeTerminal = direccionDeTerminal;
    }

    public ColaBuses getColaBuses() {
        return colaBuses;
    }

    public void setColaBuses(ColaBuses colaBuses) {
        this.colaBuses = colaBuses;
    }
}
