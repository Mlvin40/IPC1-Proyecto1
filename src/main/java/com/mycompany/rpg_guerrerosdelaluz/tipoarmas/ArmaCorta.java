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
public class ArmaCorta extends Arma {

    private double velocidadAumenta;

    public ArmaCorta() {
        super("ArmaCorta");
        this.velocidadAumenta = 6;
    }

    @Override
    public void aumentar_estadisticas(MoldeJugable jugador) {

   
        double velocidad_a_cambiar = jugador.getVelocidadTotal() + velocidadAumenta;
        jugador.setVelocidadTotal(velocidad_a_cambiar);
    }

    @Override
    public void quitar_mejora(MoldeJugable jugador) {
        double velocidad_a_cambiar = jugador.getVelocidadTotal() - velocidadAumenta;
        jugador.setVelocidadTotal(velocidad_a_cambiar);
    }
}
