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
public class ObjetoTiendaDeCampania extends Objetos {

    public ObjetoTiendaDeCampania() {
        super("TiendaDeCampaña", 150);
    }

    @Override
    public void usar(MoldeJugable guerrero) {
        //Utilizar el mismo metodo de la posada en la clase Jugador
    }
}
