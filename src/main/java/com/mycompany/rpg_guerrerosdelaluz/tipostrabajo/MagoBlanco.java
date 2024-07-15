/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rpg_guerrerosdelaluz.tipostrabajo;

import com.mycompany.rpg_guerrerosdelaluz.personajes.MoldeJugable;
import com.mycompany.rpg_guerrerosdelaluz.tipoarmas.Arma;
import com.mycompany.rpg_guerrerosdelaluz.tipoarmas.Baculo;

/**
 *
 * @author Phoenix
 */
public class MagoBlanco extends DMagos {

    private static final int ESPIRITU_POR_NIVEL = 1;
    private static final int CONCENTRACION_POR_NIVEL = 1;

    private double fuerzaDisminuye;
    private double defensaAumenta;

    public MagoBlanco() {
        super("MagoBlanco");
        this.fuerzaDisminuye = -0.50;
        this.defensaAumenta = 0.15;
    }

    @Override
    public void actualizarEstadisticas(MoldeJugable jugador) {

        //Para disminuir la fuerza actual del jugador o bot
        double fuerza_a_cambiar = jugador.getDanioBase() * fuerzaDisminuye;
        jugador.setFuerzaTotal(fuerza_a_cambiar + jugador.getDanioBase());

        //Para aumentar la defensa actual del jugador o bot
        double defensa_a_cambiar = jugador.getDefensaBase() * defensaAumenta;
        jugador.setDefensaTotal(defensa_a_cambiar + jugador.getDefensaBase());
    }

    @Override
    public void informacionDelTrabajo() {
        System.out.println("Puede usar solo báculos como armas y permite usar magia blanca .\n"
                + "La habilidad especial es poder usar la magia blanca Divinidad que causa daño a los enemigos y cura a\n"
                + "los aliados en un 40% del daño hecho.\n"
                + "La fuerza disminuye en un 50% de los puntos de fuerza actuales,\n"
                + "La defensa aumenta en un 15% de los puntos de defensa actuales.\n"
                + "Al subir de nivel con este trabajo, se suma 1 punto más al espíritu y a la concentración.");
    }

    @Override
    public void mostrar_armas_permitidas() {
        System.out.println("LAS ARMAS PERMITIDAS PARA ESTE TRABAJO SON");
        System.out.println("Baculos");
    }

    @Override
    public boolean agregarArma(Arma armaPropuesta) {
        if (armaPropuesta instanceof Baculo) {
            arma_en_posesion = armaPropuesta;
            return true;
        } else {
            mostrar_armas_permitidas();
            return false;
        }
    }
}
