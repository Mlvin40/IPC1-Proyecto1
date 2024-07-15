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
public class Escudo extends Arma {

    private double defensaAumenta;

    public Escudo() {
        super("Escudo");
        this.defensaAumenta = 6;
    }

    @Override
    public void aumentar_estadisticas(MoldeJugable jugador) {
        double defensa_a_cambiar = jugador.getDefensaTotal() + defensaAumenta;
        jugador.setDefensaTotal(defensa_a_cambiar);

    }

    @Override
    public void quitar_mejora(MoldeJugable jugador) {
        double defensa_a_cambiar = jugador.getDefensaTotal() - defensaAumenta;
        jugador.setDefensaTotal(defensa_a_cambiar);
    }
}
