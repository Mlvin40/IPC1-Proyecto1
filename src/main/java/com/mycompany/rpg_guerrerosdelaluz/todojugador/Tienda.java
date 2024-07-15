/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rpg_guerrerosdelaluz.todojugador;

import com.mycompany.rpg_guerrerosdelaluz.objetoss.ObjetoFreno;
import com.mycompany.rpg_guerrerosdelaluz.objetoss.ObjetoPlumaFenix;
import com.mycompany.rpg_guerrerosdelaluz.objetoss.ObjetoPocion;
import com.mycompany.rpg_guerrerosdelaluz.objetoss.ObjetoPocionMayor;
import com.mycompany.rpg_guerrerosdelaluz.objetoss.ObjetoTiendaDeCampania;
import com.mycompany.rpg_guerrerosdelaluz.objetoss.ObjetoVelocidad;
import com.mycompany.rpg_guerrerosdelaluz.objetoss.Objetos;
import com.mycompany.rpg_guerrerosdelaluz.otros.Herramientas;
import com.mycompany.rpg_guerrerosdelaluz.personajes.MoldeJugable;
import com.mycompany.rpg_guerrerosdelaluz.tipoarmas.Arma;
import com.mycompany.rpg_guerrerosdelaluz.tipoarmas.ArmaCorta;
import com.mycompany.rpg_guerrerosdelaluz.tipoarmas.ArmaDosManos;
import com.mycompany.rpg_guerrerosdelaluz.tipoarmas.ArmaUnaMano;
import com.mycompany.rpg_guerrerosdelaluz.tipoarmas.Baculo;
import com.mycompany.rpg_guerrerosdelaluz.tipoarmas.Escudo;
import com.mycompany.rpg_guerrerosdelaluz.tipostrabajo.Guerrero;
import java.util.Scanner;

/**
 *
 * @author Phoenix
 */
public class Tienda {

    Herramientas h = new Herramientas();

    Jugador jugador;

    //PARA OBTENER LOS PRECIOS DE COMPRA EN LA TIENDA.. VERIFICAR EL ORDEN EN EL METODO iniciar_Objetos_Tienda 
    Objetos[] Objetos_Tienda = new Objetos[6];

    public Tienda(Jugador jugador) {
        this.jugador = jugador;
        iniciar_Objetos_Tienda();
    }

    private void iniciar_Objetos_Tienda() {

        Objetos_Tienda[0] = new ObjetoPocion();
        Objetos_Tienda[1] = new ObjetoPocionMayor();
        Objetos_Tienda[2] = new ObjetoPlumaFenix();
        Objetos_Tienda[3] = new ObjetoTiendaDeCampania();
        Objetos_Tienda[4] = new ObjetoVelocidad();
        Objetos_Tienda[5] = new ObjetoFreno();
    }

    public void INICIAR_TIENDA() {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            h.limpiarPantalla();
            h.separadorLineas();
            System.out.println("1.Comprar Objetos");
            System.out.println("2.Vender Objetos");
            h.separadorLineas();
            System.out.println("3.Magias");
            System.out.println("");
            System.out.println("4.ComprarArmas");
            System.out.println("5.Vender Armas");
            System.out.println("");
            System.out.println("6.Comprar Trabajos");
            System.out.println("7.Vender Trabajos");
            System.out.println("8.Salir");
            h.separadorLineas();
            System.out.println("Que desea realizar?");

            try {
                opcion = Integer.valueOf(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un numero entero válido");
                opcion = 0;
            }

            switch (opcion) {
                case 1:
                    h.limpiarPantalla();
                    comprarObjetos();
                    break;

                case 2:
                    h.limpiarPantalla();
                    venderObjetos();
                    break;

                case 3:
                    //Comprar Magias
                    break;
                case 4:
                    //Comprar Armas
                    h.limpiarPantalla();
                    comprarArmas();
                    break;

                case 5:
                    //Vender Armar
                    h.limpiarPantalla();
                    venderArmas();
                    break;

                case 6:
                    //ComprarTrabajos
                    break;

                case 7:
                    //Vender Trabajos
                    break;

                default:
            }
        } while (opcion != 8);
    }

    private void venderObjetos() {
        Scanner scanner = new Scanner(System.in);
        int objeto_a_Vender = 0;

        do {
            h.limpiarPantalla();
            h.separadorLineas();
            jugador.inventario.mostrarInventario();
            h.separadorLineas();
            System.out.println("ORO DISPONIBLE: " + jugador.getOro());
            for (int i = 0; i < Objetos_Tienda.length; i++) {
                Objetos_Tienda[i].precio_de_Venta(i + 1);
            }
            h.separadorLineas();
            System.out.println("12.SALIR");

            try {
                objeto_a_Vender = Integer.valueOf(scanner.nextLine());
            } catch (NumberFormatException e) {

                System.out.println("Error: Ingrese un numero entero válido");
                objeto_a_Vender = 0;
            }

            switch (objeto_a_Vender) {
                case 1:
                    //POCION
                    if (jugador.inventario.quitarPocion()) {
                        Aniadir_Oro(Objetos_Tienda[0]);
                    }
                    break;

                case 2:
                    //POCION_MAYOR
                    if (jugador.inventario.quitarPocionMayor()) {
                        Aniadir_Oro(Objetos_Tienda[1]);
                    }

                    break;

                case 3:
                    //PlumaFenix
                    if (jugador.inventario.quitarPlumaFenix()) {
                        Aniadir_Oro(Objetos_Tienda[2]);
                    }
                    break;

                case 4:
                    //TiendaDeCampaña
                    if (jugador.inventario.quitarTiendaDeCampa()) {
                        Aniadir_Oro(Objetos_Tienda[3]);
                    }

                    break;
                case 5:
                    //Velocidad
                    if (jugador.inventario.quitarVelocidad()) {
                        Aniadir_Oro(Objetos_Tienda[4]);
                    }

                    break;

                case 6:
                    //Freno;
                    if (jugador.inventario.quitarFreno()) {
                        Aniadir_Oro(Objetos_Tienda[5]);
                    }

                    break;

                default:
            }
        } while (objeto_a_Vender != 12);

    }

    private void comprarObjetos() {

        Scanner scanner = new Scanner(System.in);
        int objeto_a_Comprar = 0;

        do {
            h.limpiarPantalla();
            h.separadorLineas();
            System.out.println("ORO DISPONIBLE: " + jugador.getOro());
            for (int i = 0; i < Objetos_Tienda.length; i++) {
                Objetos_Tienda[i].precio_de_Compra(i + 1);
            }
            h.separadorLineas();
            System.out.println("12.SALIR");

            try {
                objeto_a_Comprar = Integer.valueOf(scanner.nextLine());
            } catch (NumberFormatException e) {

                System.out.println("Error: Ingrese un numero entero válido");
                objeto_a_Comprar = 0;
            }

            switch (objeto_a_Comprar) {
                case 1:
                    //POCION
                    if (verificar_Si_Alcanza(Objetos_Tienda[0])) {
                        jugador.inventario.agregarPocion();
                    }

                    break;

                case 2:
                    //POCION_MAYOR
                    if (verificar_Si_Alcanza(Objetos_Tienda[1])) {
                        jugador.inventario.agregarPocionMayor();
                    }
                    break;

                case 3:
                    //PlumaFenix
                    if (verificar_Si_Alcanza(Objetos_Tienda[2])) {
                        jugador.inventario.agregarPlumaFenix();
                    }
                    break;

                case 4:
                    //TiendaDeCampaña
                    if (verificar_Si_Alcanza(Objetos_Tienda[3])) {
                        jugador.inventario.agregarTiendaDeCampania();
                    }
                    break;
                case 5:
                    //Velocidad
                    if (verificar_Si_Alcanza(Objetos_Tienda[4])) {
                        jugador.inventario.agregarVelocidad();
                    }
                    break;

                case 6:
                    //Freno;
                    if (verificar_Si_Alcanza(Objetos_Tienda[5])) {
                        jugador.inventario.agregarFreno();
                    }
                    break;
                default:

            }
        } while (objeto_a_Comprar != 12);
    }

    private void comprarArmas() {
        Scanner scanner = new Scanner(System.in);

        h.limpiarPantalla();
        h.separadorLineas();

        System.out.println("ORO DISPONIBLE: " + jugador.getOro());
        System.out.println("Precio Por Arma: 100");
        System.out.println("A que guerrero le quiere comprar un arma?");
        MoldeJugable guerreroElegido = seleccionarGuerrero();
        System.out.println("");
        System.out.println("1. Arma de una Mano");
        System.out.println("2. Arma de dos Manos");
        System.out.println("3. Arma Corta");
        System.out.println("4. Escudo");
        System.out.println("5. Baculo");
        int objeto_a_Comprar = 0;

        try {
            objeto_a_Comprar = Integer.valueOf(scanner.nextLine());
        } catch (NumberFormatException e) {

            System.out.println("Error: Ingrese un numero entero válido");
            objeto_a_Comprar = 0;
        }

        switch (objeto_a_Comprar) {
            case 1:
                if (guerreroElegido.getTrabajoActivo().getArma_en_posesion() != null) {
                    System.out.println("Este guerrero tiene un arma.. vendelo para poder comprar otro");
                } else {
                    ArmaUnaMano aramArmaUnaMano = new ArmaUnaMano();
                    cobrarArma(guerreroElegido, aramArmaUnaMano);
                }
                break;

            case 2:
                if (guerreroElegido.getTrabajoActivo().getArma_en_posesion() != null) {
                    System.out.println("Este guerrero tiene un arma.. vendelo para poder comprar otro");
                } else {
                    ArmaDosManos armaDosManos = new ArmaDosManos();
                    cobrarArma(guerreroElegido, armaDosManos);
                }
                break;

            case 3:

                if (guerreroElegido.getTrabajoActivo().getArma_en_posesion() != null) {
                    System.out.println("Este guerrero tiene un arma.. vendelo para poder comprar otro");
                } else {
                    ArmaCorta armaCorta = new ArmaCorta();
                    cobrarArma(guerreroElegido, armaCorta);
                }
                break;
            case 4:
                if (guerreroElegido.getTrabajoActivo().getArma_en_posesion() != null) {
                    System.out.println("Este guerrero tiene un arma.. vendelo para poder comprar otro");
                } else {
                    Escudo escudo = new Escudo();
                    cobrarArma(guerreroElegido, escudo);
                }
                break;

            case 5:
                if (guerreroElegido.getTrabajoActivo().getArma_en_posesion() != null) {
                    System.out.println("Este guerrero tiene un arma.. vendelo para poder comprar otro");
                } else {
                    Baculo baculo = new Baculo();
                    cobrarArma(guerreroElegido, baculo);
                }
                break;
            default:

        }
    }

    private void venderArmas() {
        Scanner scanner = new Scanner(System.in);

        h.limpiarPantalla();
        h.separadorLineas();

        System.out.println("Recibes por Arma: 100");
        System.out.println("Selecciona el guerrero al que deseas venderle el arma");
        MoldeJugable guerreroElegido = seleccionarGuerrero();

        if (guerreroElegido.getTrabajoActivo().getArma_en_posesion() != null) {
            System.out.println("Venta Exitosa");
            guerreroElegido.getTrabajoActivo().getArma_en_posesion().quitar_mejora(guerreroElegido);
            //Con este metodo busca cuando vale el arma y se lo reembolsa al jugador
            jugador.setOro(jugador.getOro() + guerreroElegido.getTrabajoActivo().getArma_en_posesion().getPrecio());
            guerreroElegido.getTrabajoActivo().setArma_en_posesion(null);

        } else {
            System.out.println(guerreroElegido.getNombre() + " Actualmente no tiene un arma");
        }
        h.enterParaContinuar();
    }

    private MoldeJugable seleccionarGuerrero() {

        int opcion = -1;

        MoldeJugable[] guerrerosLuz = jugador.getGuerrerosLuz();

        Scanner scanner = new Scanner(System.in);
        do {
            for (int i = 0; i < guerrerosLuz.length; i++) {
                System.out.println(i + " " + guerrerosLuz[i].getNombre() + " " + guerrerosLuz[i].getTrabajoActivo().getNombre());
            }

            System.out.println("Seleccione un Guerrero (0-3):");
            try {
                opcion = Integer.valueOf(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese solo números.");
                opcion = -1; // Restablece la opción a un valor inválido para volver a solicitarla
            }

            if (opcion < 0 || opcion > 3) {
                System.out.println("Error: Ingrese un número válido dentro del rango 0-3.");
            }
        } while (opcion < 0 || opcion > 3);

        return guerrerosLuz[opcion];

    }

    //METODO QUE SIRVE PARA VER SI HAY SUFICIENTE ORO PARA COMPRAR ALGUN OBJETO
    private boolean verificar_Si_Alcanza(Objetos objetoTienda) {
        boolean seCompro;

        if ((jugador.getOro() - objetoTienda.getPrecio()) < 0) {
            System.out.println("Oro Insuficiente.. Compra No Realizada");
            seCompro = false;
            h.enterParaContinuar();
        } else {
            System.out.println("Compra Exitosa");
            jugador.setOro(jugador.getOro() - objetoTienda.getPrecio());
            seCompro = true;
            h.enterParaContinuar();
        }
        return seCompro;
    }

    private void cobrarArma(MoldeJugable guerrero, Arma arma) {

        if ((jugador.getOro() - arma.getPrecio()) < 0) {
            System.out.println("Oro insuficiente");
        } else {
            if (guerrero.getTrabajoActivo().agregarArma(arma)) {
                guerrero.getTrabajoActivo().getArma_en_posesion().aumentar_estadisticas(guerrero);
                System.out.println("Compra Exitosa");
                jugador.setOro(jugador.getOro() - arma.getPrecio());
            }
        }
        h.enterParaContinuar();
    }

    private void Aniadir_Oro(Objetos objeto_a_Vender) {
        jugador.setOro(jugador.getOro() + objeto_a_Vender.getPrecio());
    }
}
