/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rpg_guerrerosdelaluz.otros;

import com.mycompany.rpg_guerrerosdelaluz.ControladorBatallaCiudad;
import com.mycompany.rpg_guerrerosdelaluz.Controladores;
import com.mycompany.rpg_guerrerosdelaluz.personajes.MoldeJugable;
import com.mycompany.rpg_guerrerosdelaluz.todojugador.Jugador;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Phoenix
 */
public class AccionesParaBatallas {

    Colores c = new Colores();
    Herramientas h = new Herramientas();
    Random random = new Random();

    //****************************METODOS PARA LAS BATALLAS**************************************
    //para ver si alguno se queda sin jugadores
    public boolean equiposinJugadores(MoldeJugable[] losenviado) {
        for (MoldeJugable grupo : losenviado) {
            if (grupo.getPuntosdevidaTotal() > 0) {
                //si alguno esta muerto el return no se activa
                return false;
            }
        }

        return true;
    }//fin metodo verificar jugadores

    private void estadisticas_En_Tiempo_Real(MoldeJugable[] equipo) {
        for (MoldeJugable jgador : equipo) {
            jgador.mostrarEstadisticasEnBatalla();
            h.separadorLineas();
        }
    }

    /**
     * Busca a un guerrero que este vivo, si todos estan muertos sale del ciclo
     *
     * @param guerreroLuz
     * @param bots
     * @param campoDeBatalla
     */
    public void ataqueDeLosBots(MoldeJugable[] guerreroLuz, MoldeJugable[] bots, Controladores campoDeBatalla) {
        for (MoldeJugable bot : bots) {
            h.saltoDeLinea();
            if (bot.estaVivo()) {
                //logica ataque

                int aleatorio;
                do {
                    aleatorio = random.nextInt(4);
                    //Si todos los guerreros de la luz estan muerto se sale del bucle que busca a uno vivo
                    if (equiposinJugadores(guerreroLuz)) {
                        break;
                    }
                } while (!guerreroLuz[aleatorio].estaVivo());

                //Al revisar nuevamente que todos los guerreros de la luz murieron no se ejecuta el ataque y sale de la partida
                if (equiposinJugadores(guerreroLuz)) {
                    campoDeBatalla.setPartidaEnCurso(false); // para indicar que la partida termino
                    break;
                }
                System.out.println(bot.getNombre() + " ha atacado a " + guerreroLuz[aleatorio].getNombre());
                bot.realizarAtaque(guerreroLuz[aleatorio]);

            } else {
                System.out.println(bot.getNombre() + " Esta muerto");
            }

        }

        h.saltoDeLinea();
        estadisticas_En_Tiempo_Real(guerreroLuz);
        h.enterParaContinuar();
    }

    //metodo que es utilizado por el jugador para decidir a que enemigo quiere atacar
    public int atacar_a_bot(MoldeJugable[] losEnviados) {

        int opcion = -1;
        boolean entradaValida = false;
        Scanner scanner = new Scanner(System.in);

        do {

            for (MoldeJugable losbots : losEnviados) {
                losbots.getNombre();
            }
            try {

                System.out.println("Elija un objetivo");
                opcion = scanner.nextInt();

                if (opcion >= 0 && opcion < losEnviados.length) {
                    entradaValida = true;
                } else {
                    System.out.println("Ingrese un numero dentro del rango válido (0-" + (losEnviados.length - 1) + ").");

                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese un numero entero válido");
                scanner.nextLine();
            }

        } while (!entradaValida);
        return opcion;
    }

    /**
     * Por si los guerreros de la luz mueren en batalla se utiliza este metodo
     *
     * @param guerreros
     */
    public void recuperarUnoDeVida(MoldeJugable[] guerreros) {
        for (MoldeJugable guerrero : guerreros) {
            guerrero.setPuntosdevidaTotal(1);
        }
    }

    /**
     * Define quien es el ganador de la partida, en caso de ser el jugador se
     * entregan los premios correspondientes al tipo de batalla
     *
     * @param guerreros
     * @param mensajeDerrota
     * @param experiencia_por_victoria
     * @param controladorPartida
     */
    public void definirGanadorDeLaPartida(Jugador jugador, MoldeJugable[] guerreros, String mensajeDerrota, int experiencia_por_victoria, Controladores controladorPartida, int oro_por_Victoria) {
        if (equiposinJugadores(guerreros)) {
            System.out.println(mensajeDerrota);
            recuperarUnoDeVida(guerreros);
            if (controladorPartida instanceof ControladorBatallaCiudad) {
                ((ControladorBatallaCiudad) controladorPartida).setGANO_LA_BATALLA(false);
            }
        } else {
            System.out.println("Los guerreros con vida ganan " + experiencia_por_victoria + " puntos de experiencia");
            h.saltoDeLinea();
            for (MoldeJugable guerrero : guerreros) {
                if (guerrero.estaVivo()) {
                    guerrero.ganarPartida(experiencia_por_victoria);
                }
            }
            System.out.println("has ganado " + oro_por_Victoria + " de oro");
            h.saltoDeLinea();
            jugador.ganarOroPorVictoria(oro_por_Victoria);
            if (controladorPartida instanceof ControladorBatallaCiudad) {
                ((ControladorBatallaCiudad) controladorPartida).setGANO_LA_BATALLA(true);
            }
        }
    }

    /**
     * PARA EL ATAQUE DEL EQUIPO ALIADO (GUERREROS DE LA LUZ)
     *
     * @param equipoAlidado
     * @param equipoEnemigo
     * @param campoDeBatalla
     */
    public void hacer_Ataque_Por_Equipo(MoldeJugable[] equipoAlidado, MoldeJugable[] equipoEnemigo, Controladores campoDeBatalla, Jugador jugador) {

        //ANTES DE ENTRAR A REALIZAR LOS ATAQUES SE ORDENAN LOS GUERREROS DEL MAS RAPIDO AL MAS LENTO
        jugador.ordenarPorVelocidad(equipoAlidado);
        //TERMINA EL ORDENAMIENTO BURBUJA

        estadisticas_En_Tiempo_Real(equipoAlidado);
        System.out.println("Atacaran en el orden mostrado");
        h.enterParaContinuar();
        h.saltoDeLinea();
        for (MoldeJugable guerrero : equipoAlidado) {

            //TURNO DE LOS GUERREROS
            //antes de entrar al turno verifica si el guerrero esta vivo
            if (guerrero.estaVivo()) {
                estadisticas_En_Tiempo_Real(equipoEnemigo);
                System.out.println("turno de:" + guerrero.getNombre());

                //Aqui implementar el menuaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
                switch (MenuDeAtaque()) {
                    case 1:
                        guerrero.mostrarEstadisticasEnBatalla();
                        guerrero.realizarAtaque(equipoEnemigo[atacar_a_bot(equipoEnemigo)]);
                        break;

                    case 2:
                        jugador.getInventario().usarObjetoEnBatalla();
                        break;

                    case 3:
                        System.out.println(guerrero.getNombre() + " Salto el turno");
                        break;
                    default:
                }
                //Depues de que un guerrero de la luz ataca revisa si aun existen CaballerosOscuridad vivos
                if (equiposinJugadores(equipoEnemigo)) {
                    estadisticas_En_Tiempo_Real(equipoEnemigo);
                    campoDeBatalla.setPartidaEnCurso(false);
                    break;
                }

            } else {
                System.out.println("Guerrero :" + guerrero.getNombre() + " Muerto");
            }
            h.enterParaContinuar();
        }
        //FIN DEL TURNO DE LOS GUERREROS
    }

    public void mensajeInicioDeBatalla() {
        h.separadorLineas();
        System.out.println(c.amarillo("la batalla ha comenzado"));
        h.separadorLineas();
        h.saltoDeLinea();
        h.enterParaContinuar();
    }

    public int MenuDeAtaque() {
        Scanner scanner = new Scanner(System.in);
        boolean opcionValida = false;
        int opcion = 0;

        do {
            try {
                System.out.println("1. Atacar");
                System.out.println("2. Objeto");
                System.out.println("3. Saltar");

                opcion = Integer.valueOf(scanner.nextLine());

                if (opcion >= 1 && opcion <= 3) {
                    opcionValida = true;
                } else {
                    System.out.println("La opción debe ser un número entre 1 y 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Debes ingresar un número válido.");
            }
        } while (!opcionValida);

        return opcion;
    }
}
