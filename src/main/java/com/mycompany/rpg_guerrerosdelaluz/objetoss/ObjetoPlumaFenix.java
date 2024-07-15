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
public class ObjetoPlumaFenix extends Objetos {

    private int PuntosPorResureccion;

    public ObjetoPlumaFenix() {
        super("PlumaFenix", 100);
        this.PuntosPorResureccion = 35;
    }

    @Override
    public void usar(MoldeJugable guerrero) {
        if (!guerrero.estaVivo()) {
            guerrero.setPuntosdevidaTotal(PuntosPorResureccion);
            System.out.println(guerrero.getNombre() + " Revive y recupera " + PuntosPorResureccion + " puntos de vida");
            SE_USO = true;
        } else {
            System.out.println("Solo puedes aplicas Pluma Fenix a un guerero exhausto");
            SE_USO = false;
        }
    }
}
