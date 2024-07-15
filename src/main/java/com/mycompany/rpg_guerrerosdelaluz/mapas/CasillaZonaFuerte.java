/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rpg_guerrerosdelaluz.mapas;

import java.util.Random;

/**
 *
 * @author Phoenix
 */
public class CasillaZonaFuerte extends Casillas {

    private static int cantidadagenerar;

    public static final String ROJO = "\u001B[31m";

    public CasillaZonaFuerte() {
    }

    public CasillaZonaFuerte(int catidadagenerar) {
        this.cantidadagenerar = catidadagenerar;
    }

    @Override
    public String icono() {
        return ROJO + "~" + Casillas.RESET;
    }

    public static int getCantidadagenerar() {
        return cantidadagenerar;
    }

    @Override
    public void agregarAlMapa(String[][] mapa_batalla, int tamanioMapa) {
        Random random = new Random();

        for (int i = 0; i < getCantidadagenerar(); i++) {

            int rndFila;
            int rndColumna;

            rndFila = random.nextInt(tamanioMapa);
            rndColumna = random.nextInt(tamanioMapa);

            if (mapa_batalla[rndFila][rndColumna] != icono()) {

                mapa_batalla[rndFila][rndColumna] = icono();
            }
        }
    }
}
