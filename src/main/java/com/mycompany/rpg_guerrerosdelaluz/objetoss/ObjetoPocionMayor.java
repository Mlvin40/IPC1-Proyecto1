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
public class ObjetoPocionMayor extends Objetos {

    private int PuntosRecuperado;

    public ObjetoPocionMayor() {
        super("PocionMayor", 80);
        this.PuntosRecuperado = random.nextInt(11) + 25; // Rango 25-35
    }

    @Override
    public void usar(MoldeJugable guerrero) {

        if (guerrero.estaVivo()) {
            guerrero.setPuntosdevidaTotal(guerrero.getPuntosdevidaTotal() + PuntosRecuperado);
            System.out.println("Guerrero " + guerrero.getNombre() + " Ha recuperado " + PuntosRecuperado + " de vida");
            if (guerrero.getPuntosdevidaBase() < guerrero.getPuntosdevidaTotal()) {
                double restar_exceso = guerrero.getPuntosdevidaTotal() % guerrero.getPuntosdevidaBase();
                guerrero.setPuntosdevidaTotal(guerrero.getPuntosdevidaTotal() - restar_exceso);
                SE_USO = true;
            }

        } else {
            System.out.println("No puedes aplicar una pocion a un guerrero exhausto");
            SE_USO = false;
        }
        h.enterParaContinuar();

    }
}
