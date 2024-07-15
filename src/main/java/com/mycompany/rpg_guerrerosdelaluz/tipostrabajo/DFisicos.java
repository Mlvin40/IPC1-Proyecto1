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
public abstract class DFisicos extends Trabajo {


    

    public DFisicos(String nombre) {
        super(nombre);
    }

    @Override
    public abstract void actualizarEstadisticas(MoldeJugable jugador);
    
    @Override
    public abstract void informacionDelTrabajo();
    
    @Override
    public abstract void mostrar_armas_permitidas();
    
    @Override
    public abstract void ganar_estadisticas_por_nivel(MoldeJugable guerrero);
    
    @Override
    public abstract boolean agregarArma(Arma armaPropuesta);
}
