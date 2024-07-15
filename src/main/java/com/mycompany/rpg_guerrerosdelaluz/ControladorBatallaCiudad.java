/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rpg_guerrerosdelaluz;

import com.mycompany.rpg_guerrerosdelaluz.otros.AccionesParaBatallas;
import com.mycompany.rpg_guerrerosdelaluz.otros.Colores;
import com.mycompany.rpg_guerrerosdelaluz.otros.Herramientas;
import com.mycompany.rpg_guerrerosdelaluz.todojugador.Jugador;
import com.mycompany.rpg_guerrerosdelaluz.personajes.Bot;
import com.mycompany.rpg_guerrerosdelaluz.personajes.MoldeJugable;
import java.util.Random;

/**
 *
 * @author Phoenix
 */
public class ControladorBatallaCiudad extends Controladores {

    int experiencia_por_victoria = 100;

    //Esta variable sirve para decirle al mapa si el juagador gano la partida y asi cambiar el estado de la ciudad a reconquistada
    private boolean GANO_LA_BATALLA;

    Colores c = new Colores();

    Random random = new Random();
    Herramientas h = new Herramientas();
    AccionesParaBatallas accionesBatalla = new AccionesParaBatallas();

    Jugador jugador;
    MoldeJugable[] GuerrerosLuz;
    Bot[] CaballerosOscuridad = new Bot[4];

    String mensajeDerrota = "Los guerreros de la oscuridad te han derrotado";

    //CONSTRUCTOR
    public ControladorBatallaCiudad(Jugador jugador) {
        this.jugador = jugador;
        OroPorVictoria = 75;
        GuerrerosLuz = jugador.getGuerrerosLuz();
        crearcaballerosOscuridad();
        jugador.ordenarPorVelocidad(CaballerosOscuridad);
        agregarIndiceAlBot();
        iniciar_batalla();
    }

    ///*********METODO PRINCIPAL**********///////
    public void iniciar_batalla() {
        accionesBatalla.mensajeInicioDeBatalla();
        // boolean partidaencurso = true;
        //inicia la batalla

        //Mientras la variable partidaEnCurso de la clase padre no cambie a false significa que la partida aun esta funcionando
        do {
            jugador.ordenarPorVelocidad(GuerrerosLuz);

            //TURNO DE LOS GUERREROS DE LA LUZ
            accionesBatalla.hacer_Ataque_Por_Equipo(GuerrerosLuz, CaballerosOscuridad, this, jugador);
            //FIN DEL TURNO DE LOS GUERREROS

            //INICIO ATAQUE DE LOS BOTS
            accionesBatalla.ataqueDeLosBots(GuerrerosLuz, CaballerosOscuridad, this);
            //FIN ATAQUE DE LOS BOTS

            //Verifica que Guerreros de la luz Murieron
        } while (partidaEnCurso);

        accionesBatalla.definirGanadorDeLaPartida(jugador, GuerrerosLuz, mensajeDerrota, experiencia_por_victoria, this, OroPorVictoria);
        //VER QUIEN GANO LA PARTIDA    

        //Para cuantos caballeros de la oscuridad murieron
        caballerosDerrotados();
    }

    //metodo que es utilizado para atacar a un enemigo en especifico
    private void crearcaballerosOscuridad() {

        for (int i = 0; i < CaballerosOscuridad.length; i++) {
            System.out.println("Se ha generado");

            CaballerosOscuridad[i] = new Bot();
            CaballerosOscuridad[i].puntosEstadistica();

        }
    }

    //Metodo para agregarle un numero de objetivo a cada enemigo que los definira en batalla.. se agrega despues de haber ordenado a los enemigos por velocidad
    private void agregarIndiceAlBot() {
        for (int i = 0; i < CaballerosOscuridad.length; i++) {

            CaballerosOscuridad[i].setNombre(CaballerosOscuridad[i].getNombre() + c.rojo(" [" + i + "]"));
        }
    }

    public boolean isGANO_LA_BATALLA() {
        return GANO_LA_BATALLA;
    }

    public void setGANO_LA_BATALLA(boolean GANO_LA_BATALLA) {
        this.GANO_LA_BATALLA = GANO_LA_BATALLA;
    }

    private void caballerosDerrotados() {
        for (MoldeJugable cOscuridad : CaballerosOscuridad) {
            if (!cOscuridad.estaVivo()) {
                jugador.incrementarDerrotasCaballeroOscuridad();
            }
        }
    }
}
