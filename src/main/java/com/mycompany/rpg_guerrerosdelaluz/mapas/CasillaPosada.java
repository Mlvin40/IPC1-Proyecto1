/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rpg_guerrerosdelaluz.mapas;

import com.mycompany.rpg_guerrerosdelaluz.todojugador.Jugador;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Phoenix
 */
public class CasillaPosada extends Casillas {

    private int precioPosada = 150;

    CasillaCiudad casillaCiudad = new CasillaCiudad();
    CasillaZonaFuerte casillaZonaFuerte = new CasillaZonaFuerte();
    CasillaTienda casillaTienda = new CasillaTienda();

    @Override
    public String icono() {
        return c.AMARILLO + "P" + c.RESET;
    }

    public void agregarAlMapa(String[][] mapa_batalla, int tamanioMapa) {

        Random random = new Random();

        int rndFila;
        int rndColumna;

        do {
            rndFila = random.nextInt(tamanioMapa);
            rndColumna = random.nextInt(tamanioMapa);

        } while ((mapa_batalla[rndFila][rndColumna].equals(casillaCiudad.icono())) || mapa_batalla[rndFila][rndColumna].equals(casillaZonaFuerte.icono())
                || mapa_batalla[rndFila][rndColumna].equals(casillaTienda.icono()));
        mapa_batalla[rndFila][rndColumna] = icono();
    }

    public void INICIAR_POSADA(Jugador jugador) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Pagar Posada (" + precioPosada + " Oro)");
        System.out.println("2. Salir");
        System.out.println("Desea Pagar la Posada?");
        int opcion = 0;

        try {
            opcion = Integer.valueOf(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingrese un numero entero válido");
            scanner.nextLine();
        }
        switch (opcion) {
            case 1:
                //Procedimiento posada
                if (jugador.getOro() >= precioPosada) {
                    jugador.setOro(jugador.getOro() - precioPosada);
                    jugador.recuperarVidaYMagias();
                } else {
                    System.out.println("Oro insuficiente");
                    h.enterParaContinuar();
                }

                break;

            case 2:
                break;

            default:
                System.out.println("Opcion no valida");
        }
    }
}
