/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rpg_guerrerosdelaluz.mapas;

import com.mycompany.rpg_guerrerosdelaluz.otros.Colores;
import com.mycompany.rpg_guerrerosdelaluz.otros.Herramientas;

/**
 *
 * @author Phoenix
 */
public abstract class Casillas {

    //HERRAMIENTAS QUE SERVIRAN EN CLASESE HIJAS
    Herramientas h = new Herramientas();
    Colores c = new Colores();

    protected static final String RESET = "\u001B[0m";

    public abstract String icono();

    public abstract void agregarAlMapa(String mapa_batalla[][], int tamanioMapa);

}
