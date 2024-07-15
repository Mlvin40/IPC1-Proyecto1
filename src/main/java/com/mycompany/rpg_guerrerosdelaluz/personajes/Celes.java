/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rpg_guerrerosdelaluz.personajes;

import com.mycompany.rpg_guerrerosdelaluz.tipostrabajo.MagoBlanco;

/**
 *
 * @author Phoenix
 */
public class Celes extends MoldeJugable {

    public Celes() {
        super(AZUL + "CELES" + RESET);

        trabajosMaximos[0] = new MagoBlanco();
        trabajoActivo = trabajosMaximos[0];

        experiencia = 0;

        aumentarEstadisticasPorTrabajo();
    }

    @Override
    public void aumentarEstadisticasPorTrabajo() {
        trabajoActivo.actualizarEstadisticas(this);
    }
}
