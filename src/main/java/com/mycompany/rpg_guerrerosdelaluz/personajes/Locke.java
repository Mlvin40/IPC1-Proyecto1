/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rpg_guerrerosdelaluz.personajes;

import com.mycompany.rpg_guerrerosdelaluz.tipostrabajo.Ninja;

/**
 *
 * @author Phoenix
 */
public class Locke extends MoldeJugable {

    public Locke() {
        super(AZUL + "LOCKE" + RESET);
        
        trabajosMaximos[0] = new Ninja();
        trabajoActivo = trabajosMaximos[0];

        experiencia = 0;
        aumentarEstadisticasPorTrabajo();
    }

    @Override
    public void aumentarEstadisticasPorTrabajo() {
        trabajoActivo.actualizarEstadisticas(this);
    }

}
