/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rpg_guerrerosdelaluz.todojugador;

import com.mycompany.rpg_guerrerosdelaluz.otros.Colores;
import com.mycompany.rpg_guerrerosdelaluz.otros.Herramientas;
import com.mycompany.rpg_guerrerosdelaluz.personajes.Celes;
import com.mycompany.rpg_guerrerosdelaluz.personajes.Locke;
import com.mycompany.rpg_guerrerosdelaluz.personajes.MoldeJugable;
import com.mycompany.rpg_guerrerosdelaluz.personajes.Rydia;
import com.mycompany.rpg_guerrerosdelaluz.personajes.Vann;

/**
 *
 * @author Phoenix
 */
public class Jugador {

    Colores c = new Colores();

    private int oro = 1000;

    private int fuegosDerrotados = 0;
    private int hielosDerrotados = 0;
    private int neutrosDerrotados = 0;
    private int oscuridadDerrotados = 0;

    Herramientas h = new Herramientas();
    private int Total_De_Ciudades;
    private int Reconquistadas;

    public void setReconquistadas(int Reconquistadas) {
        this.Reconquistadas = Reconquistadas;
    }

    private String nombreJugador;
    private MoldeJugable[] guerrerosLuz = new MoldeJugable[4];

    Inventario inventario = new Inventario(this);

    public Jugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
        this.guerrerosLuz[0] = new Vann();
        this.guerrerosLuz[1] = new Celes();
        this.guerrerosLuz[2] = new Locke();
        this.guerrerosLuz[3] = new Rydia();

        ordenarPorVelocidad(guerrerosLuz);
    }

    public void verJugadoresActuales() {
        for (MoldeJugable guerrero : guerrerosLuz) {
            guerrero.puntosEstadistica();

        }
        System.out.println("");
        System.out.println("Oro disponible: " + oro);
        System.out.println("");
        inventario.mostrarInventario();
    }

    //METODO UTILIZADO POR LA POSADA, CIUDAD RECONQUISTADA Y TIENDA DE CAMPAÑA
    public void recuperarVidaYMagias() {
        for (MoldeJugable guerrero : guerrerosLuz) {
            if (guerrero.getTrabajoActivo().getArma_en_posesion() != null) {
                guerrero.getTrabajoActivo().getArma_en_posesion().quitar_mejora(guerrero);
            }

            guerrero.setPuntosdevidaTotal(guerrero.getPuntosdevidaBase());
            guerrero.aumentarEstadisticasPorTrabajo();

            //solo si el guerrero tiene un arma se activa esto
            if (guerrero.getTrabajoActivo().getArma_en_posesion() != null) {
                guerrero.getTrabajoActivo().getArma_en_posesion().aumentar_estadisticas(guerrero);
            }

        }
        System.out.println("Todos los guerreros de la luz han recuperado su HP");
        h.enterParaContinuar();
    }

    public void mostrarReportes() {
        h.saltoDeLinea();
        h.separadorLineas();
        System.out.println(c.ROJO + "¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤ ~~~~~" + nombreJugador + "~~~~~ ¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤" + c.RESET);
        System.out.println(c.verde("---ENEMIGOS DERROTADOS---"));
        System.out.println("Caballeros De La Oscuridad: " + oscuridadDerrotados);
        System.out.println("Tipo Fuego: " + fuegosDerrotados);
        System.out.println("Tipo Hielo: " + hielosDerrotados);
        System.out.println("Tipo Neutro: " + neutrosDerrotados);
        System.out.println(c.verde("---CANTIDAD DE VECES QUE CADA GUERRERO MURIO---"));
        for (MoldeJugable guerrero : guerrerosLuz) {
            System.out.println(guerrero.getNombre() + ": " + guerrero.getCantidadDeMuertes());
        }
        System.out.println(c.verde("---CANTIDAD DE DAÑO QUE CADA GUERRERO REALIZO---"));
        for (MoldeJugable guerrero : guerrerosLuz) {
            System.out.println(guerrero.getNombre() + ": " + guerrero.getCantidadDe_T_DanioRelizado());
        }
        System.out.println("Ciudades Reconquistadas: " + Reconquistadas);
        System.out.println(definirSiGanoOAbandono());
        h.separadorLineas();
    }

    private String definirSiGanoOAbandono() {
        if (Total_De_Ciudades == 0) {
            return "Reconquisto Todas Las Ciudades";
        } else {
            return "Abandono la Partida";
        }
    }

    //METODO DE ORDENAMIENTO BURBUJA UTILIZADO PARA DEFINIR QUE JUGADOR ATACARA DE PRIMERO EN LAS BATALLAS
    public void ordenarPorVelocidad(MoldeJugable[] jugadores) {

        int n = jugadores.length;
        boolean intercambio;

        do {
            intercambio = false;
            for (int i = 1; i < n; i++) {
                if (jugadores[i - 1].getVelocidadTotal() < jugadores[i].getVelocidadTotal()) {

                    // Intercambiar a los jugadores si están en el orden incorrecto - de mayor a menor
                    MoldeJugable temp = jugadores[i - 1];
                    jugadores[i - 1] = jugadores[i];
                    jugadores[i] = temp;
                    intercambio = true;
                }
            }
            n--; // CON ESTO INDICAMOS QUE YA NO TOME EN CUENTA A LOS JUGADORES ORDENADOS AL INICIAR NUEVAMENTE EL FOR
        } while (intercambio);
    }

    public void ganarOroPorVictoria(int oroGanado) {
        oro += oroGanado;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public int getTotal_De_Ciudades() {
        return Total_De_Ciudades;
    }

    public void setTotal_De_Ciudades(int Total_De_Ciudades) {
        this.Total_De_Ciudades = Total_De_Ciudades;
    }

    public MoldeJugable[] getGuerrerosLuz() {
        return guerrerosLuz;
    }

    public void setGuerrerosLuz(MoldeJugable[] jugadores) {
        this.guerrerosLuz = jugadores;
    }

    public int getOro() {
        return oro;
    }

    public void setOro(int oro) {
        this.oro = oro;
    }

    public void enemigoTipoFuegoDerrotado() {
        fuegosDerrotados++;
    }

    public void enemigoTipoHieloDerrotado() {
        hielosDerrotados++;
    }

    public void enemigoTipoNeutroDerrotado() {
        neutrosDerrotados++;
    }

    public void incrementarDerrotasCaballeroOscuridad() {
        oscuridadDerrotados++;
    }
}
