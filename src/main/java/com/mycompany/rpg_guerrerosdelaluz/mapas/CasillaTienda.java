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
public class CasillaTienda extends Casillas {

    CasillaCiudad casillaCiudad = new CasillaCiudad();
    CasillaZonaFuerte casillaZonaFuerte = new CasillaZonaFuerte();

    @Override
    public String icono() {
        return c.AMARILLO + "T" + c.RESET;
    }

    @Override
    public void agregarAlMapa(String[][] mapa_batalla, int tamanioMapa) {
        Random random = new Random();

        int rndFila;
        int rndColumna;

        do {
            rndFila = random.nextInt(tamanioMapa);
            rndColumna = random.nextInt(tamanioMapa);

        } while ((mapa_batalla[rndFila][rndColumna].equals(casillaCiudad.icono())) || mapa_batalla[rndFila][rndColumna].equals(casillaZonaFuerte.icono()));

        mapa_batalla[rndFila][rndColumna] = icono();
    }
}
