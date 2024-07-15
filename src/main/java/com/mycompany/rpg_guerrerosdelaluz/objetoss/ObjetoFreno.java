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
public class ObjetoFreno extends Objetos {

    private int cantidadFreno;

    public ObjetoFreno() {
        super("Freno", 60);
        this.cantidadFreno = 6;
    }

    @Override
    public void usar(MoldeJugable guerrero) {

        if ((guerrero.getVelocidadBase() - cantidadFreno) > 0) {
            guerrero.setVelocidadBase(guerrero.getVelocidadBase() - cantidadFreno);
            guerrero.setVelocidadTotal(guerrero.getVelocidadBase());
            guerrero.aumentarEstadisticasPorTrabajo();
            System.out.println(guerrero.getNombre() + " Disminuye su velocidad en " + cantidadFreno + " puntos");
            SE_USO = true;
        } else {
            System.out.println("No puedes aplicarle mas freno a " + guerrero.getNombre());
            SE_USO = false;
        }
    }
}
