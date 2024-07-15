/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rpg_guerrerosdelaluz.tipoenemigo;

/**
 *
 * @author Phoenix
 */
public class TipoNeutro extends Enemigo {

    public TipoNeutro(int num) {
        super(elegirNombre() + " TipoNeutro " + "[" + num + "]");
    }
}
