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
public class MagoOscuro extends DMagos {

    private double fuerzaDisminuye;
    private double defensaAumenta;

    public MagoOscuro() {
        super("MagoOscuro");
        this.fuerzaDisminuye = -0.60;
        this.defensaAumenta = 0.15;
    }

    @Override
    public void actualizarEstadisticas(MoldeJugable jugador) {

        //Para disminuir la fuerza del jugador o bot
        double fuerza_a_cambiar = jugador.getDanioBase() * fuerzaDisminuye;
        jugador.setFuerzaTotal(fuerza_a_cambiar + jugador.getDanioBase());

        //Para aumentar la defensa del jugador o bot
        double defensa_a_cambiar = jugador.getDefensaBase() * defensaAumenta;
        jugador.setDefensaTotal(defensa_a_cambiar + jugador.getDefensaBase());

    }

    @Override
    public void informacionDelTrabajo() {
        System.out.println("Puede usar solo báculos como armas y permite usar magia oscura.\n"
                + "La habilidad especial es poder usar la magia oscura Meteoro que causa daño a los enemigos y puede\n"
                + "hacer que pierdan un turno en una probabilidad de 35%.\n"
                + "La fuerza disminuye en un 60% de los puntos de fuerza actuales,\n"
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
