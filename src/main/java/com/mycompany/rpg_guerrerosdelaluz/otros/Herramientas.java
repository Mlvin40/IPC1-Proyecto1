/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rpg_guerrerosdelaluz.otros;

import com.mycompany.rpg_guerrerosdelaluz.mapas.MapaAleatorio;
import com.mycompany.rpg_guerrerosdelaluz.mapas.Mapa;
import java.util.Scanner;

/**
 *
 * @author Phoenix
 */
public class Herramientas {

    Colores c = new Colores();

    public void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void borrarLinea() {
        System.out.print("\033[F\033[2K");
    }

    public void mensajeMovimiento(int ciudades_A_Reconquistar) {
        System.out.println(c.azul("                                                            UsarObjeto (u)"));
        System.out.println(c.naranja("                  Arriba (w)") + "               " + c.azul("                     Informacion (i)") + c.verde("        CiudadesPorReconquistar ") + ciudades_A_Reconquistar);
        System.out.println(c.NARANJA + "Izquieda(a)      Abajo (s)       Derecha(d)" + c.RESET + c.rojo("            Abandonar Partida(p)"));
        System.out.println("ingrese una opcion");

    }

    public void mensajedeBienvenida(MapaAleatorio mapacreado, Mapa elcreado) {
        System.out.println("BIENVENIDO " + elcreado.getNameplayer() + " -SE HA CREADO UN MAPA DE TAMAÑO:" + mapacreado.getDimensionDelmapa() + "X" + mapacreado.getDimensionDelmapa());
    }

    public void pedirNombre(Mapa elcreado) {

        Scanner scanner = new Scanner(System.in);
        String nombre;
        System.out.println("Bienvenido.. Ingese su nombre");
        nombre = scanner.nextLine();

        elcreado.setNameplayer(nombre);
    }

    public void enterParaContinuar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(c.amarillo("Presione enter para continuar"));
        scanner.nextLine();
    }

    public void separadorLineas() {
        System.out.println(c.cian("--------------------------------------------------------------------"));
    }

    public void saltoDeLinea() {
        System.out.println("\n");

    }
}
