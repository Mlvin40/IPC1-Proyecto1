/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rpg_guerrerosdelaluz.tipoarmas;

import com.mycompany.rpg_guerrerosdelaluz.personajes.MoldeJugable;

/**
 *
 * @author Phoenix
 */
public class ArmaUnaMano extends Arma {

    private double fuerzaAumenta;

    public ArmaUnaMano() {
        super("ArmaUnaMano");
        this.fuerzaAumenta = 12;
    }

    @Override
    public void aumentar_estadisticas(MoldeJugable jugador) {

        //la fuerza aumenta
        double fuerza_a_cambiar = jugador.getFuerzaTotal() + fuerzaAumenta;
        jugador.setFuerzaTotal(fuerza_a_cambiar);

    }

    @Override
    public void quitar_mejora(MoldeJugable jugador) {
        double fuerza_a_cambiar = jugador.getFuerzaTotal() - fuerzaAumenta;
        jugador.setFuerzaTotal(fuerza_a_cambiar);

    }
}
