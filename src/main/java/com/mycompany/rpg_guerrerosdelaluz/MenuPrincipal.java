/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rpg_guerrerosdelaluz;

import com.mycompany.rpg_guerrerosdelaluz.mapas.Mapa;
import com.mycompany.rpg_guerrerosdelaluz.otros.Colores;
import com.mycompany.rpg_guerrerosdelaluz.otros.Herramientas;
import com.mycompany.rpg_guerrerosdelaluz.todojugador.Jugador;
import java.util.Scanner;

/**
 *
 * @author Phoenix
 */
public class MenuPrincipal {

    private int partidasIniciada = 0;
    Jugador[] TodosLosJugadores = new Jugador[0];

    Colores c = new Colores();
    Herramientas h = new Herramientas();

    public void mostrar() {
        Scanner scanner = new Scanner(System.in);

        int opcion = 0;

        do {
            h.separadorLineas();
            System.out.println(c.azul("BIENVENIDO AL RPG"));
            h.separadorLineas();
            h.separadorLineas();
            System.out.println(c.verde("1. Iniciar Partida"));
            h.separadorLineas();
            h.separadorLineas();
            System.out.println(c.verde("2. Mostrar Estadisticas"));
            h.separadorLineas();
            h.separadorLineas();
            System.out.println(c.verde("3. Salir"));
            h.separadorLineas();

            try {
                opcion = Integer.valueOf(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un numero entero válido");
                opcion = 0;
            }

            switch (opcion) {
                case 1:
                    partidasIniciada++;
                    h.limpiarPantalla();
                    Mapa mapa = new Mapa();
                    mapa.iniciarPartida();

                    //Al finalizar una partida aumenta el tamaño del arreglo Jugadores en la cantidad de partidas iniciadas
                    TodosLosJugadores = aumentarJugadores();
                    TodosLosJugadores[partidasIniciada - 1] = mapa.getPlayer();
                    break;

                case 2:
                    h.limpiarPantalla();
                    System.out.println("Mostrando estadisticas");
                    mostrarEstadisticasDeLosJugadores();

                    break;

                case 3:
                    h.limpiarPantalla();
                    System.out.println(c.azul("HASTA PRONTO ;)"));
                    h.enterParaContinuar();
                    break;
                default:
            }
        } while (opcion != 3);
    }

    private Jugador[] aumentarJugadores() {
        //Crea un nuevo arreglo con el tamaño de la cantidad de partidas iniciadas
        Jugador[] nuevosJugadores = new Jugador[partidasIniciada];

        //Pasar los jugadores acutales al nuevo con dimension aumentada
        for (int i = 0; i < TodosLosJugadores.length; i++) {
            nuevosJugadores[i] = TodosLosJugadores[i];
        }
        return nuevosJugadores;
    }

    private void mostrarEstadisticasDeLosJugadores() {

        for (int i = 0; i < TodosLosJugadores.length; i++) {
            TodosLosJugadores[i].mostrarReportes();
        }
    }
}
