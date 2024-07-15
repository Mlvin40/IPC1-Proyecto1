/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rpg_guerrerosdelaluz.mapas;

/**
 *
 * @author Phoenix
 */
public class CasillaZonaDebil extends Casillas {

    private static final String VERDE = "\u001B[32m";

    @Override
    public String icono() {
        return VERDE + "-" + Casillas.RESET;
    }

    @Override
    public void agregarAlMapa(String[][] mapa_batalla, int tamanioMapa) {
        //icono();
    }
}
