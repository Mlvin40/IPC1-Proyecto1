/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rpg_guerrerosdelaluz.objetoss;

import com.mycompany.rpg_guerrerosdelaluz.otros.Colores;
import com.mycompany.rpg_guerrerosdelaluz.otros.Herramientas;
import com.mycompany.rpg_guerrerosdelaluz.personajes.MoldeJugable;
import java.util.Random;

/**
 *
 * @author Phoenix
 */
public abstract class Objetos {

    protected Random random = new Random();
    protected boolean SE_USO;

    Herramientas h = new Herramientas();
    Colores c = new Colores();

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
    protected String nombre;
    protected int precio;

    public Objetos(String nombre, int precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public abstract void usar(MoldeJugable guerrero);

    public boolean isSE_USO() {
        return SE_USO;
    }

    public void precio_de_Compra(int numeroEnTienda) {

        h.separadorLineas();
        System.out.println(c.naranja(numeroEnTienda + "." + nombre) + " Precio de Compra: " + precio);

    }

    public void precio_de_Venta(int numeroEnTienda) {

        h.separadorLineas();
        System.out.println(c.naranja(numeroEnTienda + "." + nombre) + " Recibes Por Vender: " + precio);

    }
}
