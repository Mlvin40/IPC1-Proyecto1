/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rpg_guerrerosdelaluz.tipostrabajo;

import com.mycompany.rpg_guerrerosdelaluz.personajes.MoldeJugable;
import com.mycompany.rpg_guerrerosdelaluz.tipoarmas.Arma;
import com.mycompany.rpg_guerrerosdelaluz.tipoarmas.ArmaDosManos;
import com.mycompany.rpg_guerrerosdelaluz.tipoarmas.ArmaUnaMano;

/**
 *
 * @author Phoenix
 */
public class Guerrero extends DFisicos {

    private static final int FUERZA_POR_NIVEL = 1;

    private double fuerzaAumenta;
    private double velocidadDisminuye;

    public Guerrero() {
        super("Guerrero");
        this.fuerzaAumenta = 0.25;
        this.velocidadDisminuye = -0.15;
    }

    @Override
    public void actualizarEstadisticas(MoldeJugable jugador) {

        //la fuerza del jugador o bot aumenta
        double fuerza_a_cambiar = jugador.getDanioBase() * fuerzaAumenta;
        jugador.setFuerzaTotal(fuerza_a_cambiar + jugador.getDanioBase());

        //la velocidad del jugador o bot reduce
        double velocidad_a_cambiar = jugador.getVelocidadBase() * velocidadDisminuye;
        jugador.setVelocidadTotal(jugador.getVelocidadBase() + velocidad_a_cambiar);

    }

    @Override
    public void informacionDelTrabajo() {
        System.out.println("Aumenta la fuerza física y permite al personaje usar hasta dos armas de una mano, o una arma pesada.\n"
                + "Tiene la habilidad especial de hacer daño de área al golpear a un enemigo.\n"
                + "La fuerza se aumenta en un 25% de los puntos de fuerza actual\n"
                + "La velocidad disminuye en un 15% de los puntos de velocidad actual.\n"
                + "Al subir de nivel con este trabajo, se suma 1 punto mas a la fuerza.");
    }

    @Override
    public void mostrar_armas_permitidas() {
        System.out.println("LAS ARMAS PERMITIDAS PARA ESTE TRABAJO SON");
        System.out.println("Arma de una mano");
        System.out.println("Arma de dos manos");

    }

    @Override
    public void ganar_estadisticas_por_nivel(MoldeJugable guerrero) {

        System.out.println(guerrero.getNombre() + " Por Trabajo " + getNombre() + " se suma " + FUERZA_POR_NIVEL + " punto a la fuerza");
        guerrero.setDanioBase(guerrero.getDanioBase() + FUERZA_POR_NIVEL);
        h.separadorLineas();
    }

    @Override
    public boolean agregarArma(Arma armaPropuesta) {
        if (armaPropuesta instanceof ArmaUnaMano || armaPropuesta instanceof ArmaDosManos) {
            arma_en_posesion = armaPropuesta;
            return true;
        } else {
            mostrar_armas_permitidas();
            return false;
        }
    }
}
