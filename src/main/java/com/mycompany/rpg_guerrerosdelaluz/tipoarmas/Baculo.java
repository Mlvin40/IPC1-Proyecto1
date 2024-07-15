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
public class Baculo extends Arma {

    private double concentracionAumenta;
    private double vidaAumenta;

    public Baculo() {
        super("Baculo");
        this.concentracionAumenta = 6;
        this.vidaAumenta = 10;
    }

    @Override
    public void aumentar_estadisticas(MoldeJugable jugador) {

        double concentracion_a_cambiar = jugador.getConcentracionTotal() + concentracionAumenta;
        jugador.setConcentracionTotal(concentracion_a_cambiar);

        double vida_a_cambiar = jugador.getPuntosdevidaTotal() + vidaAumenta;
        jugador.setPuntosdevidaTotal(vida_a_cambiar);

    }

    @Override
    public void quitar_mejora(MoldeJugable jugador) {
        double concentracion_a_cambiar = jugador.getConcentracionTotal() - concentracionAumenta;
        jugador.setConcentracionTotal(concentracion_a_cambiar);

        double vida_a_cambiar = jugador.getPuntosdevidaTotal() - jugador.getPuntosdevidaTotal() - vidaAumenta;
        jugador.setPuntosdevidaTotal(vida_a_cambiar);
    }
}
