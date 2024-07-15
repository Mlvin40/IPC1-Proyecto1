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
public abstract class Arma {

    private int precio = 100;

    public int getPrecio() {
        return precio;
    }

    private String nombre;

    public Arma(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public abstract void aumentar_estadisticas(MoldeJugable jugador);
    public abstract void quitar_mejora(MoldeJugable jugador);
    
}
