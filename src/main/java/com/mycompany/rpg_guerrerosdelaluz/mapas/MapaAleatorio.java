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
public class MapaAleatorio {

    private int dimensionDelmapa;

    public MapaAleatorio() {
        this.dimensionDelmapa = elegir_mapa_aleatorio();

    }

    private int elegir_mapa_aleatorio() {

        Random random = new Random();

        int opcion = random.nextInt(3) + 1;

        switch (opcion) {
            case 1:
                return 7;

            case 2:
                return 10;

            case 3:
                return 12;

            default:
                throw new AssertionError();
        }
    }

    public int getDimensionDelmapa() {
        return dimensionDelmapa;
    }
}
