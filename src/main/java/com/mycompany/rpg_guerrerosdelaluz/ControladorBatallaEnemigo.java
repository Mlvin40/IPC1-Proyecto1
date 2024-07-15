/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rpg_guerrerosdelaluz;

import com.mycompany.rpg_guerrerosdelaluz.otros.AccionesParaBatallas;
import com.mycompany.rpg_guerrerosdelaluz.otros.Colores;
import com.mycompany.rpg_guerrerosdelaluz.otros.Herramientas;
import com.mycompany.rpg_guerrerosdelaluz.personajes.MoldeJugable;
import com.mycompany.rpg_guerrerosdelaluz.tipoenemigo.Enemigo;
import com.mycompany.rpg_guerrerosdelaluz.tipoenemigo.TipoFuego;
import com.mycompany.rpg_guerrerosdelaluz.tipoenemigo.TipoHielo;
import com.mycompany.rpg_guerrerosdelaluz.tipoenemigo.TipoNeutro;
import com.mycompany.rpg_guerrerosdelaluz.todojugador.Jugador;
import java.util.Random;

/**
 *
 * @author Phoenix
 */
public class ControladorBatallaEnemigo extends Controladores {

    int experiencia_por_victoria = 20;

    Colores c = new Colores();

    Random random = new Random();
    Herramientas h = new Herramientas();
    AccionesParaBatallas accionesBatalla = new AccionesParaBatallas();

    Jugador jugador;
    MoldeJugable[] guerrerosLuz;

    Enemigo[] enemigos = new Enemigo[random.nextInt(2) + 1];

    String mensajeDerrota = "Los enemigos te ha derrotado";

    public ControladorBatallaEnemigo(Jugador jugador) {

        this.jugador = jugador;
        OroPorVictoria = 15;
        guerrerosLuz = jugador.getGuerrerosLuz();
        crearEnemigos(enemigos);
        iniciar_batalla();
    }

    private void iniciar_batalla() {
        h.separadorLineas();
        c.amarillo("La batalla ha comenzado");
        h.separadorLineas();
        h.saltoDeLinea();

        //inicia la batalla
        do {
            jugador.ordenarPorVelocidad(guerrerosLuz);

            //TURNO DE LOS GUERREROS DE LA LUZ
            accionesBatalla.hacer_Ataque_Por_Equipo(guerrerosLuz, enemigos, this, jugador);
            //FIN DEL TURNO DE LOS GUERREROS DE LA LUZ

            //INICIO ATAQUE DE LOS BOTS
            accionesBatalla.ataqueDeLosBots(guerrerosLuz, enemigos, this);
            //FIN ATAQUE DE LOS BOTS

        } while (partidaEnCurso);

        //los enemigos derrotados
        accionesBatalla.definirGanadorDeLaPartida(jugador, guerrerosLuz, mensajeDerrota, experiencia_por_victoria, this, OroPorVictoria);

        //Para ver que tipo de enmigos fue derrotado y agregarlo a los reportes
        enemigosDerrotados();
    }

    //metodo que es utilizado para crear a los diferentes tipos de enemigos que se puede encontrar en una partida
    private void crearEnemigos(Enemigo[] enemigos) {
        for (int i = 0; i < enemigos.length; i++) {

            int numero = random.nextInt(3);

            switch (numero) {
                case 0:
                    enemigos[i] = new TipoFuego(i);

                    break;

                case 1:
                    enemigos[i] = new TipoHielo(i);

                    break;

                case 2:
                    enemigos[i] = new TipoNeutro(i);
                    break;

                default:
                    throw new AssertionError();
            }
            enemigos[i].detallesBot();
            System.out.println("\n");
        }
    }

    //PARA LLEVAR UN CONTROL DE CUANTOS ENEMIGOS DE CADA TIPO SON DERROTADOS
    private void enemigosDerrotados() {
        for (MoldeJugable enemigoD : enemigos) {

            if (!enemigoD.estaVivo()) {

                if (enemigoD instanceof TipoFuego) {
                    jugador.enemigoTipoFuegoDerrotado();
                } else if (enemigoD instanceof TipoHielo) {
                    jugador.enemigoTipoHieloDerrotado();
                } else if (enemigoD instanceof TipoNeutro) {
                    jugador.enemigoTipoNeutroDerrotado();
                }
            }
        }
    }
}
