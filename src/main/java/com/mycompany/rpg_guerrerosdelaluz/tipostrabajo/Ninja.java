/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rpg_guerrerosdelaluz.tipostrabajo;

import com.mycompany.rpg_guerrerosdelaluz.personajes.MoldeJugable;
import com.mycompany.rpg_guerrerosdelaluz.tipoarmas.Arma;
import com.mycompany.rpg_guerrerosdelaluz.tipoarmas.ArmaCorta;

/**
 *
 * @author Phoenix
 */
public class Ninja extends DFisicos {

    //la cantidad de vida que aumenta al subir de nivel
    private static final int VIDA_POR_NIVEL = 10;

    private double velocidadAumenta;
    private double defensaDisminuye;

    public Ninja() {
        super("Ninja");
        this.velocidadAumenta = 0.35;
        this.defensaDisminuye = -0.20;
    }

    @Override
    public void actualizarEstadisticas(MoldeJugable jugador) {

        //Para cambiar la velocidad actual del jugador o bot
        double velocidad_a_cambiar = jugador.getVelocidadBase() * velocidadAumenta;
        jugador.setVelocidadTotal(velocidad_a_cambiar + jugador.getVelocidadBase());

        //Para cambiar la velocidad actual del jugador o bot
        double defensa_a_cambiar = jugador.getDefensaBase() * defensaDisminuye;
        jugador.setDefensaTotal(defensa_a_cambiar + jugador.getDefensaBase());

    }

    @Override
    public void informacionDelTrabajo() {
        System.out.println("Aumenta la velocidad y permite al personaje usar hasta dos armas cortas.\n"
                + "Tiene la habilidad especial de evadir ataques basado en una probabilidad de 35% de evasión.\n"
                + "La velocidad aumenta en un 35% de los puntos de velocidad actuales.\n"
                + "La defensa disminuye en un 20% de los puntos de defensa actuales.\n"
                + "Al subir de nivel con este trabajo, se suma 10 puntos más a los puntos de vida.");
    }

    @Override
    public void mostrar_armas_permitidas() {
        System.out.println("LAS ARMAS PERMITIDAS PARA ESTE TRABAJO SON");
        System.out.println("Arma corta");
    }

    @Override
    public void ganar_estadisticas_por_nivel(MoldeJugable guerrero) {

        System.out.println(guerrero.getNombre() + " Por trabajo " + getNombre() + " la vida aumenta " + VIDA_POR_NIVEL + " puntos mas");
        guerrero.setPuntosdevidaBase(guerrero.getPuntosdevidaBase() + VIDA_POR_NIVEL);
        h.separadorLineas();

    }

    @Override
    public boolean agregarArma(Arma armaPropuesta) {
        if (armaPropuesta instanceof ArmaCorta) {
            arma_en_posesion = armaPropuesta;
            return true;
        }
        mostrar_armas_permitidas();
        return false;
    }

}
