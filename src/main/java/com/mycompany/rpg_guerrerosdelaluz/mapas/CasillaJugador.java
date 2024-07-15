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
public class CasillaJugador extends Casillas {

    CasillaCiudad casillaCiudad = new CasillaCiudad();
    CasillaZonaFuerte casillaZonaFuerte = new CasillaZonaFuerte();
    CasillaTienda casillaTienda = new CasillaTienda();
    CasillaPosada casillaPosada = new CasillaPosada();

    private int posicionX;
    private int posicionY;

    private static final String AZUL = "\u001B[34m";
    private static final String RESET = "\u001B[0m";

    @Override
    public String icono() {
        return AZUL + "¤" + RESET;
    }

    @Override
    public void agregarAlMapa(String[][] mapa_batalla, int tamanioMapa) {
        Random random = new Random();

        int rndFila;
        int rndColumna;

        do {
            rndFila = random.nextInt(tamanioMapa);
            rndColumna = random.nextInt(tamanioMapa);

        } while ((mapa_batalla[rndFila][rndColumna].equals(casillaCiudad.icono())) || mapa_batalla[rndFila][rndColumna].equals(casillaZonaFuerte.icono())
                || mapa_batalla[rndFila][rndColumna].equals(casillaTienda.icono()) || mapa_batalla[rndFila][rndColumna].equals(casillaPosada.icono()));

        mapa_batalla[rndFila][rndColumna] = icono();
        this.posicionX = rndFila;
        this.posicionY = rndColumna;

        //while ((mapa_batalla[rndFila][rndColumna] != casillaCiudad.icono()) && (mapa_batalla[rndFila][rndColumna] != casillaZonaFuerte.icono()));
    }

    public int getPosicionX() {
        return posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }

}
