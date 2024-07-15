/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rpg_guerrerosdelaluz.tipostrabajo;

import com.mycompany.rpg_guerrerosdelaluz.otros.Herramientas;
import com.mycompany.rpg_guerrerosdelaluz.personajes.MoldeJugable;
import com.mycompany.rpg_guerrerosdelaluz.tipoarmas.Arma;

/**
 *
 * @author Phoenix
 */
public abstract class Trabajo {

    Herramientas h = new Herramientas();

    //trabajo1
    //arma 2
    protected Arma arma_en_posesion;

    public Arma getArma_en_posesion() {
        return arma_en_posesion;
    }

    public void setArma_en_posesion(Arma arma_en_posesion) {
        this.arma_en_posesion = arma_en_posesion;
    }

    private String nombre;
    //private final int precioDeCompra; //precio al comprar un trabajo en la tienda es de 200 de oro 

    public Trabajo(String nombre) {
        // this.precioDeCompra = 200;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public abstract void actualizarEstadisticas(MoldeJugable jugador);

    public abstract void informacionDelTrabajo();

    public abstract void mostrar_armas_permitidas();

    public abstract void ganar_estadisticas_por_nivel(MoldeJugable guerrero);

    public abstract boolean agregarArma(Arma armaPropuesta);

}
