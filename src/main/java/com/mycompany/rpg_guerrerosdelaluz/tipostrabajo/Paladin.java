/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rpg_guerrerosdelaluz.tipostrabajo;

import com.mycompany.rpg_guerrerosdelaluz.personajes.MoldeJugable;
import com.mycompany.rpg_guerrerosdelaluz.tipoarmas.Arma;
import com.mycompany.rpg_guerrerosdelaluz.tipoarmas.ArmaUnaMano;
import com.mycompany.rpg_guerrerosdelaluz.tipoarmas.Escudo;

/**
 *
 * @author Phoenix
 */
public class Paladin extends DFisicos {

    private static final int DEFENSA_POR_NIVEL = 1;

    private double defensaAumenta;
    private double vidaAumenta;

    public Paladin() {
        super("Paladin");
        this.defensaAumenta = 0.25;
        this.vidaAumenta = 0.15;

    }

    @Override
    public void actualizarEstadisticas(MoldeJugable jugador) {

        //Para aumentar la defensa actual
        double defensa_a_cambiar = jugador.getDefensaBase() * defensaAumenta;
        jugador.setDefensaTotal(defensa_a_cambiar + jugador.getDefensaBase());

        //Para aumentar la vida
        double vida_aumentar = jugador.getPuntosdevidaBase() * vidaAumenta;
        jugador.setPuntosdevidaTotal(vida_aumentar + jugador.getPuntosdevidaBase());

    }

    @Override
    public void informacionDelTrabajo() {
        System.out.println("Trabajo bien balanceado que permite el uso de escudos y armas de una sola mano. Es el único trabajo\n"
                + "que puede llevar escudo.\n"
                + "Tiene la habilidad especial de defender de un ataque a los compañeros con su escudo en una\n"
                + "probabilidad de 35%.\n"
                + "La defensa aumenta en un 25% de los puntos de defensa actuales.\n"
                + "Los puntos de vida aumentan en un 15% de los puntos de vida actuales.\n"
                + "Al subir de nivel con este trabajo, se suma 1 punto más a la defensa.");
    }

    @Override
    public void mostrar_armas_permitidas() {
        System.out.println("LAS ARMAS PERMITIDAS PARA ESTE TRABAJO SON");
        System.out.println("Escudo");
        System.out.println("Arma de una mano");
    }

    @Override
    public void ganar_estadisticas_por_nivel(MoldeJugable guerrero) {

        System.out.println(guerrero.getNombre() + " Por trabajo " + getNombre() + " se suma " + DEFENSA_POR_NIVEL + " a la defensa");
        guerrero.setDefensaBase(guerrero.getDefensaBase() + DEFENSA_POR_NIVEL);
        h.separadorLineas();
    }

    @Override
    public boolean agregarArma(Arma armaPropuesta) {
        if (armaPropuesta instanceof ArmaUnaMano || armaPropuesta instanceof Escudo) {
            arma_en_posesion = armaPropuesta;
            return true;
        } else {
            mostrar_armas_permitidas();
            return false;
        }
    }
}
