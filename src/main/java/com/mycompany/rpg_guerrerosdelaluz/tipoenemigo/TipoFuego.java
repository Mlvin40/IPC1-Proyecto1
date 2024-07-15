/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rpg_guerrerosdelaluz.tipoenemigo;

/**
 *
 * @author Phoenix
 */
public class TipoFuego extends Enemigo {

    public TipoFuego(int indice) {
        super(elegirNombre() + " TipoFuego " + "[" + indice + "]");
    }
}
