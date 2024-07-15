/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rpg_guerrerosdelaluz.todojugador;

import com.mycompany.rpg_guerrerosdelaluz.magiass.Magias;
import com.mycompany.rpg_guerrerosdelaluz.objetoss.ObjetoFreno;
import com.mycompany.rpg_guerrerosdelaluz.objetoss.ObjetoPlumaFenix;
import com.mycompany.rpg_guerrerosdelaluz.objetoss.ObjetoPocion;
import com.mycompany.rpg_guerrerosdelaluz.objetoss.ObjetoPocionMayor;
import com.mycompany.rpg_guerrerosdelaluz.objetoss.ObjetoVelocidad;
import com.mycompany.rpg_guerrerosdelaluz.otros.Herramientas;
import com.mycompany.rpg_guerrerosdelaluz.personajes.MoldeJugable;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Phoenix
 */
public class Inventario {

    Herramientas h = new Herramientas();

    //TODO A CERCA DE LOS OBJETOS DISPONIBLES
    protected int pocion;
    protected int pocionMayor;
    protected int plumaFenix;
    protected int tiendaCampania;
    protected int velocidad;
    protected int freno;

    //TODO A CERCA DE LAS MAGIAS
    Magias[] magias = new Magias[9];

    Jugador jugador;
    MoldeJugable[] guerreros;

    public Inventario(Jugador jugador) {

        this.jugador = jugador;
        this.guerreros = jugador.getGuerrerosLuz();
        this.pocion = 8;
        this.pocionMayor = 0;
        this.plumaFenix = 2;
        this.tiendaCampania = 2;
        this.velocidad = 0;
        this.freno = 0;
    }

    public void mostrarInventario() {
        System.out.println("");
        System.out.println("Poción: " + pocion + "   PocionMayor: " + pocionMayor + "   PlumaFenix: " + plumaFenix);
        System.out.println("TiendaCampaña: " + tiendaCampania + "   Velocidad: " + velocidad + "   Freno: " + freno);
        System.out.println("");
    }

    //METODO QUE VERIFICA SI UN OBJETO ESTA DISPONIBLE O NO Y EN BASE A ESO EJECUTA UNA ACCION
    public void usarObjeto() {

        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            h.separadorLineas();
            System.out.println("1.Pocion - Diponibles: " + pocion);
            System.out.println("2.PocionMayor - Diponibles: " + pocionMayor);
            System.out.println("3.PlumaFenix - Diponibles: " + plumaFenix);
            System.out.println("4.TiendaCampaña - Diponibles: " + tiendaCampania);
            System.out.println("5.Velocidad - Diponibles: " + velocidad);
            System.out.println("6.Freno - Diponibles: " + freno);
            System.out.println("12.SALIR");
            h.separadorLineas();

            try {
                opcion = Integer.valueOf(scanner.nextLine());
            } catch (NumberFormatException e) {

                System.out.println("Error: Ingrese un numero entero válido");
                opcion = 0;
            }

            switch (opcion) {
                case 1:
                    h.limpiarPantalla();
                    if (pocion == 0) {
                        System.out.println("NO TIENES POCIONE DISPONIBLE");
                    } else {
                        ObjetoPocion objetoPocion = new ObjetoPocion();
                        objetoPocion.usar(guerreros[seleccionarGuerrero()]);
                        if (objetoPocion.isSE_USO()) {
                            pocion--;
                        }
                    }

                    break;
                case 2:
                    h.limpiarPantalla();
                    if (pocionMayor == 0) {
                        System.out.println("NO TIENES POCIONES MAYOR DISPONIBLE");
                    } else {
                        ObjetoPocionMayor objetoPocionMayor = new ObjetoPocionMayor();
                        objetoPocionMayor.usar(guerreros[seleccionarGuerrero()]);
                        if (objetoPocionMayor.isSE_USO()) {
                            pocionMayor--;
                        }
                    }

                    break;

                case 3:
                    h.limpiarPantalla();
                    if (plumaFenix == 0) {
                        System.out.println("NO TIENES PLUMAS FENIX DISPONIBLE");
                    } else {
                        ObjetoPlumaFenix objetoPlumaFenix = new ObjetoPlumaFenix();
                        objetoPlumaFenix.usar(guerreros[seleccionarGuerrero()]);
                        if (objetoPlumaFenix.isSE_USO()) {
                            plumaFenix--;
                        }
                    }

                    break;

                case 4:
                    h.limpiarPantalla();
                    if (tiendaCampania == 0) {
                        System.out.println("NO TIENES TIENDA DE CAMPAÑA DISPONIBLE");
                    } else {
                        jugador.recuperarVidaYMagias();
                        tiendaCampania--;
                    }
                    break;

                case 5:
                    h.limpiarPantalla();
                    if (velocidad == 0) {
                        System.out.println("NO TIENES VELOCIDAD DISPONIBLE");
                    } else {
                        ObjetoVelocidad objetoVelocidad = new ObjetoVelocidad();
                        objetoVelocidad.usar(guerreros[seleccionarGuerrero()]);
                        if (objetoVelocidad.isSE_USO()) {
                            velocidad--;
                            jugador.ordenarPorVelocidad(guerreros);
                        }
                    }
                    break;

                case 6:
                    h.limpiarPantalla();
                    if (freno == 0) {
                        System.out.println("NO TIENES FRENO DISPONIBLE");
                    } else {
                        ObjetoFreno objetoFreno = new ObjetoFreno();
                        objetoFreno.usar(guerreros[seleccionarGuerrero()]);
                        if (objetoFreno.isSE_USO()) {
                            freno--;
                            jugador.ordenarPorVelocidad(guerreros);
                        }
                    }

                    break;
                default:
                    h.limpiarPantalla();
            }
        } while (opcion != 12);
    }

    private int seleccionarGuerrero() {

        int opcion = -1;
        boolean entradaValida = false;
        Scanner scanner = new Scanner(System.in);
        do {

            for (int i = 0; i < guerreros.length; i++) {
                System.out.println(i + " " + guerreros[i].getNombre() + "  HP: " + guerreros[i].getPuntosdevidaTotal() + "/" + guerreros[i].getPuntosdevidaBase() + " Velocidad: " + guerreros[i].getVelocidadTotal());
            }
            try {

                System.out.println("Seleccione un Guerrero");
                opcion = scanner.nextInt();

                if (opcion >= 0 && opcion < guerreros.length) {
                    entradaValida = true;
                } else {
                    System.out.println("Elija a un guerrero valido (0-" + (guerreros.length - 1) + ").");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese un numero entero válido");
                scanner.nextLine();
            }

        } while (!entradaValida);
        return opcion;
    }

    public void usarObjetoEnBatalla() {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        boolean pasoElTurno = false;

        do {
            h.separadorLineas();
            System.out.println("1.Pocion - Diponibles: " + pocion);
            System.out.println("2.PocionMayor - Diponibles: " + pocionMayor);
            System.out.println("3.PlumaFenix - Diponibles: " + plumaFenix);
            System.out.println("4.Velocidad - Diponibles: " + velocidad);
            System.out.println("5.Freno - Diponibles: " + freno);
            System.out.println("6.Saltar Turno");
            h.separadorLineas();

            try {
                opcion = Integer.valueOf(scanner.nextLine());
            } catch (NumberFormatException e) {

                System.out.println("Error: Ingrese un numero entero válido");
                opcion = 0;
            }

            switch (opcion) {
                case 1:
                    if (pocion == 0) {
                        System.out.println("NO TIENES POCIONE DISPONIBLE");
                    } else {
                        ObjetoPocion objetoPocion = new ObjetoPocion();
                        objetoPocion.usar(guerreros[seleccionarGuerrero()]);
                        if (objetoPocion.isSE_USO()) {
                            pocion--;
                            pasoElTurno = true;
                        }
                    }

                    break;
                case 2:

                    if (pocionMayor == 0) {
                        System.out.println("NO TIENES POCIONES MAYOR DISPONIBLE");
                    } else {
                        ObjetoPocionMayor objetoPocionMayor = new ObjetoPocionMayor();
                        objetoPocionMayor.usar(guerreros[seleccionarGuerrero()]);
                        if (objetoPocionMayor.isSE_USO()) {
                            pocionMayor--;
                            pasoElTurno = true;
                        }
                    }

                    break;

                case 3:

                    if (plumaFenix == 0) {
                        System.out.println("NO TIENES PLUMAS FENIX DISPONIBLE");
                    } else {
                        ObjetoPlumaFenix objetoPlumaFenix = new ObjetoPlumaFenix();
                        objetoPlumaFenix.usar(guerreros[seleccionarGuerrero()]);
                        if (objetoPlumaFenix.isSE_USO()) {
                            plumaFenix--;
                            pasoElTurno = true;
                        }
                    }

                    break;

                case 4:

                    if (velocidad == 0) {
                        System.out.println("NO TIENES VELOCIDAD DISPONIBLE");
                    } else {
                        ObjetoVelocidad objetoVelocidad = new ObjetoVelocidad();
                        objetoVelocidad.usar(guerreros[seleccionarGuerrero()]);
                        if (objetoVelocidad.isSE_USO()) {
                            velocidad--;
                            System.out.println("Se aplicara el cambio en la siguiente ronda");
                            pasoElTurno = true;
                        }
                    }
                    break;

                case 5:

                    if (freno == 0) {
                        System.out.println("NO TIENES FRENO DISPONIBLE");
                    } else {
                        ObjetoFreno objetoFreno = new ObjetoFreno();
                        objetoFreno.usar(guerreros[seleccionarGuerrero()]);
                        if (objetoFreno.isSE_USO()) {
                            freno--;
                            System.out.println("Se aplicara el cambio en la siguiente ronda");
                            pasoElTurno = true;
                        }
                    }

                    break;
                default:
            }
        } while (!pasoElTurno && opcion != 6);
    }

    //METODOS UTILIZADOS PARA AGREGAR OBJETOS
    public void agregarPocion() {
        pocion++;
    }

    public void agregarPocionMayor() {
        pocionMayor++;
    }

    public void agregarPlumaFenix() {
        plumaFenix++;
    }

    public void agregarTiendaDeCampania() {
        tiendaCampania++;
    }

    public void agregarVelocidad() {
        velocidad++;
    }

    public void agregarFreno() {
        freno++;
    }

    public boolean quitarPocion() {

        //Si hay pociones
        if (pocion > 0) {
            pocion--;
            System.out.println("Pocion Vendida");
            h.enterParaContinuar();
            return true;
        } else {
            System.out.println("No hay Pociones en el inventario.");
            h.enterParaContinuar();
            return false;

        }
    }

    public boolean quitarPocionMayor() {
        if (pocionMayor > 0) {
            pocionMayor--;
            System.out.println("Pocion Mayor Vendida");
            h.enterParaContinuar();
            return true;
        } else {
            System.out.println("No hay Pociones Mayor en el inventario.");
            h.enterParaContinuar();
            return false;

        }
    }

    public boolean quitarPlumaFenix() {
        if (plumaFenix > 0) {
            plumaFenix--;
            System.out.println("Pluma Fenix Vendida");
            h.enterParaContinuar();
            return true;
        } else {
            System.out.println("No hay Pluma Fenix en el inventario.");
            h.enterParaContinuar();
            return false;

        }
    }

    public boolean quitarTiendaDeCampa() {
        if (tiendaCampania > 0) {
            tiendaCampania--;
            System.out.println("Tienda de Campaña Vendia");
            h.enterParaContinuar();
            return true;
        } else {
            System.out.println("No hay Tienda de Campaña en el inventario.");
            h.enterParaContinuar();
            return false;

        }
    }

    public boolean quitarVelocidad() {
        if (velocidad > 0) {
            velocidad--;
            System.out.println("Velocidad Vendida");
            h.enterParaContinuar();
            return true;
        } else {
            System.out.println("No hay Velocidad en el inventario.");
            h.enterParaContinuar();
            return false;

        }
    }

    public boolean quitarFreno() {
        if (freno > 0) {
            freno--;
            System.out.println("Freno Vendido");
            h.enterParaContinuar();
            return true;
        } else {
            System.out.println("No hay Freno en el inventario.");
            h.enterParaContinuar();
            return false;

        }
    }
}
