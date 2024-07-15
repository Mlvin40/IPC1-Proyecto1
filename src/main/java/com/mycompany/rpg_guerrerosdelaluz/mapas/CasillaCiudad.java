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
public class CasillaCiudad extends Casillas {

    CasillaZonaFuerte casillaZonaFuerte = new CasillaZonaFuerte();

    private int cantidadCiudades;

    public CasillaCiudad() {
        this.cantidadCiudades = elegirCantidadDeCiudades();

    }

    //Metodo que se encarga de definir la cantidad total de ciudades 
    private int elegirCantidadDeCiudades() {

        Random random = new Random();
        int opcion = random.nextInt(3);

        switch (opcion) {
            case 0:
                return 3;

            case 1:
                return 4;

            case 2:
                return 5;

            default:
                throw new AssertionError();
        }

    }

    @Override
    public String icono() {
        return "©";
    }

    public String Reconquistada() {
        return c.AMARILLO + "®" + c.RESET;
    }

    public static void generar(Mapa mapa) {

    }

    public int getCantidadCiudades() {
        return cantidadCiudades;
    }

    @Override
    public void agregarAlMapa(String[][] mapa_batalla, int tamanioMapa) {
        Random random = new Random();

        for (int i = 0; i < cantidadCiudades; i++) {
            int rndFila;
            int rndColumna;

            do {
                rndFila = random.nextInt(tamanioMapa);
                rndColumna = random.nextInt(tamanioMapa);
                //busca una posicion en el mapa que no tenga una casilla de zona fuerte o una ciudad.. al encontrarla se posiciona una ciudad en dicha casilla
            } while (mapa_batalla[rndFila][rndColumna].equals(icono()) || mapa_batalla[rndFila][rndColumna].equals(casillaZonaFuerte.icono()));

            mapa_batalla[rndFila][rndColumna] = icono();

        }
    }
}
