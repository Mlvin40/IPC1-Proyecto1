/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rpg_guerrerosdelaluz.objetoss;

import com.mycompany.rpg_guerrerosdelaluz.personajes.MoldeJugable;

/**
 *
 * @author Phoenix
 */
public class ObjetoVelocidad extends Objetos {

    private int velodadAumenta;

    public ObjetoVelocidad() {
        super("Velocidad", 70);
        this.velodadAumenta = 6;
    }

    @Override
    public void usar(MoldeJugable guerrero) {

        guerrero.setVelocidadBase(guerrero.getVelocidadBase() + velodadAumenta);
        guerrero.setVelocidadTotal(guerrero.getVelocidadBase());
        guerrero.aumentarEstadisticasPorTrabajo();
        System.out.println(guerrero.getNombre() + " Aumenta su velocidad en " + velodadAumenta + " puntos mas");
        SE_USO = true;

    }
}
