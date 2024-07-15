/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rpg_guerrerosdelaluz.tipostrabajo;

import com.mycompany.rpg_guerrerosdelaluz.personajes.MoldeJugable;
import com.mycompany.rpg_guerrerosdelaluz.tipoarmas.Arma;
import com.mycompany.rpg_guerrerosdelaluz.tipoarmas.ArmaUnaMano;
import com.mycompany.rpg_guerrerosdelaluz.tipoarmas.Baculo;

/**
 *
 * @author Phoenix
 */
public class MagoRojo extends DMagos {

    private double fuerzaDisminuye;

    public MagoRojo() {
        super("MagoRojo");
        this.fuerzaDisminuye = -0.25;
    }

    @Override
    public void actualizarEstadisticas(MoldeJugable jugador) {

        //Para disminuir la fuerza del jugador o bot
        double fuerza_a_cambiar = jugador.getDanioBase() * fuerzaDisminuye;
        jugador.setFuerzaTotal(fuerza_a_cambiar + jugador.getDanioBase());

    }

    @Override
    public void informacionDelTrabajo() {
        System.out.println("Un mago capaz de usar armas de una sola mano, y usar magias blancas y oscuras).\n"
                + "La fuerza disminuye en un 25% de los puntos de fuerza actuales.\n"
                + "Al subir de nivel con este trabajo, se suma 1 punto más al espíritu y a la fuerza.");
    }

    @Override
    public void mostrar_armas_permitidas() {
        System.out.println("LAS ARMAS PERMITIDAS PARA ESTE TRABAJO SON");
        System.out.println("Baculos");
        System.out.println("Arma de una mano");
    }

    @Override
    public boolean agregarArma(Arma armaPropuesta) {
        if (armaPropuesta instanceof ArmaUnaMano || armaPropuesta instanceof Baculo) {
            arma_en_posesion = armaPropuesta;
            return true;
        } else {
            mostrar_armas_permitidas();
            return false;
        }
    }
}
