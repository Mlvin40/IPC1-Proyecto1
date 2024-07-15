/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rpg_guerrerosdelaluz.tipostrabajo;

import com.mycompany.rpg_guerrerosdelaluz.personajes.MoldeJugable;
import com.mycompany.rpg_guerrerosdelaluz.tipoarmas.Arma;

/**
 *
 * @author Phoenix
 */
public abstract class DMagos extends Trabajo {

    private static final int ESPIRITU_POR_NIVEL = 1;
    private static final int CONCENTRACION_POR_NIVEL = 1;

    public DMagos(String nombre) {
        super(nombre);
    }

    @Override
    public abstract void actualizarEstadisticas(MoldeJugable jugador);

    @Override
    public abstract void informacionDelTrabajo();

    @Override
    public abstract void mostrar_armas_permitidas();

    @Override
    public void ganar_estadisticas_por_nivel(MoldeJugable guerrero) {

        System.out.println(guerrero.getNombre() + " Por trabajo " + getNombre() + " se suma " + ESPIRITU_POR_NIVEL + " puntos mas al espiritu");
        guerrero.setEspirituBase(guerrero.getEspirituBase() + ESPIRITU_POR_NIVEL);

        System.out.println(guerrero.getNombre() + " Por trabajo " + getNombre() + " se suma " + CONCENTRACION_POR_NIVEL + " puntos mas a la concentracion");
        guerrero.setConcentracionBase(guerrero.getConcentracionBase() + CONCENTRACION_POR_NIVEL);
        h.separadorLineas();
    }

    @Override
    public abstract boolean agregarArma(Arma armaPropuesta);

}
