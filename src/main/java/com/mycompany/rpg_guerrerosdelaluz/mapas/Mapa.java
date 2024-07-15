/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rpg_guerrerosdelaluz.mapas;

import com.mycompany.rpg_guerrerosdelaluz.ControladorBatallaCiudad;
import com.mycompany.rpg_guerrerosdelaluz.ControladorBatallaEnemigo;
import com.mycompany.rpg_guerrerosdelaluz.otros.Herramientas;
import com.mycompany.rpg_guerrerosdelaluz.todojugador.Jugador;
import com.mycompany.rpg_guerrerosdelaluz.todojugador.Tienda;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Phoenix
 */
public class Mapa {
    
    Scanner scanner = new Scanner(System.in);

    //Creacion del jugador
    private String nameplayer;

    //Almacena al jugador de la partida actual 
    Jugador player;
    
    public Jugador getPlayer() {
        return player;
    }

    //Enlaza al jugador con la tienda
    Tienda tienda;

    //Creacion del mapa y los tipos de casilla
    MapaAleatorio mapaAleatorio = new MapaAleatorio();
    CasillaJugador casillajugador = new CasillaJugador();
    CasillaZonaDebil casillazonadebil = new CasillaZonaDebil();
    CasillaCiudad casillaciudad = new CasillaCiudad();
    CasillaTienda casillaTienda = new CasillaTienda();
    CasillaPosada casillaPosada = new CasillaPosada();
    
    Herramientas h = new Herramientas();

    //creacion del mapa N*N de forma aleatoria
    private int tamanioMapa = mapaAleatorio.getDimensionDelmapa();
    private String mapa_batalla[][] = new String[tamanioMapa][tamanioMapa];
    
    private int posicionX;
    private int posicionY;
    private int POSICION_CIUDAD_X;
    private int POSICION_CIUDAD_Y;

    //calcula un indice un poco menor a la mitad del mapa para asignarle casillas fuertes
    CasillaZonaFuerte casillazonafuerte = new CasillaZonaFuerte((tamanioMapa * tamanioMapa) / 2);
    
    String guardarPaso = casillazonadebil.icono();
    
    private int TOTAL_DE_CIUDADES = casillaciudad.getCantidadCiudades();
    private int CIUDADES_RECONQUISTADAS = 0;

    //Al instanciar un mapa el constructor crea el mapa automaticamente
    public Mapa() {
        h.pedirNombre(this);
        //crea a un nuevo jugador con el nombre asignado 
        this.player = new Jugador(nameplayer);
        this.tienda = new Tienda(player);
        h.mensajedeBienvenida(mapaAleatorio, this);
    }
    
    public void iniciarPartida() {
        crearMapa();
        agregarcasillaFuerte();
        agregarCiudades();
        casillaTienda.agregarAlMapa(mapa_batalla, tamanioMapa);
        casillaPosada.agregarAlMapa(mapa_batalla, tamanioMapa);
        agregariconoJugador();

        //para crear el bucle de movimiento
        movimientoJugador();
    }
    
    private void crearMapa() {
        
        for (int x = 0; x < tamanioMapa; x++) {
            for (int y = 0; y < tamanioMapa; y++) {
                
                mapa_batalla[x][y] = casillazonadebil.icono();
            }
            System.out.println("");
        }
    }
    
    public void mostrarMapa() {
        for (int x = 0; x < tamanioMapa; x++) {
            for (int y = 0; y < tamanioMapa; y++) {
                System.out.print("|" + mapa_batalla[x][y] + "|");
            }
            System.out.println("");
        }
    }

    //Metodo para el movimiento del jugador en el mapa
    private void movimientoJugador() {
        String opcion = "";
        do {
            caracterJugador();
            mostrarMapa();
            
            h.mensajeMovimiento(TOTAL_DE_CIUDADES);
            opcion = scanner.nextLine();
            
            switch (opcion) {
                case "w":
                    if (posicionX - 1 >= 0) {
                        cambiarDePosicion(posicionX - 1, posicionY);
                    }
                    
                    break;
                
                case "s":
                    if (posicionX + 1 < tamanioMapa) {
                        cambiarDePosicion(posicionX + 1, posicionY);
                    }
                    break;
                
                case "a":
                    if (posicionY - 1 >= 0) {
                        cambiarDePosicion(posicionX, posicionY - 1);
                    }
                    break;
                
                case "d":
                    if (posicionY + 1 < tamanioMapa) {
                        cambiarDePosicion(posicionX, posicionY + 1);
                    }
                    break;
                
                case "i":
                    player.verJugadoresActuales();
                    break;
                
                case "u":
                    player.getInventario().usarObjeto();
                    break;
                
                default:
                    break;
            }
            
        } while (!opcion.equals("p") && TOTAL_DE_CIUDADES != 0);
        
        finDeLaPartida();
        
    }
    
    private void agregarcasillaFuerte() {
        casillazonafuerte.agregarAlMapa(mapa_batalla, tamanioMapa);
    }
    
    private void agregarCiudades() {
        casillaciudad.agregarAlMapa(mapa_batalla, tamanioMapa);
    }
    
    private void caracterJugador() {
        mapa_batalla[posicionX][posicionY] = casillajugador.icono();
    }
    
    private void agregariconoJugador() {
        casillajugador.agregarAlMapa(mapa_batalla, tamanioMapa);
        posicionX = casillajugador.getPosicionX();
        posicionY = casillajugador.getPosicionY();
    }
    
    private void devolvercasillaPisada() {
        if (guardarPaso.equalsIgnoreCase(casillazonadebil.icono())) {
            mapa_batalla[posicionX][posicionY] = casillazonadebil.icono();
        } else if (guardarPaso.equalsIgnoreCase(casillazonafuerte.icono())) {
            mapa_batalla[posicionX][posicionY] = casillazonafuerte.icono();
        }
    }
    
    private void iniciarbatallaenCiudad() {
        h.limpiarPantalla();
        System.out.println("se ha iniciado una batalla");
        h.enterParaContinuar();
        ControladorBatallaCiudad batalla = new ControladorBatallaCiudad(player);
        if (batalla.isGANO_LA_BATALLA()) {
            TOTAL_DE_CIUDADES--;
            CIUDADES_RECONQUISTADAS++;
            mapa_batalla[POSICION_CIUDAD_X][POSICION_CIUDAD_Y] = casillaciudad.Reconquistada();
        }
    }
    
    private boolean probabilidadDeAparicionEnemigo() {
        Random random = new Random();
        int probabilidad = random.nextInt(101);

        //Probabilidad de econtrar enemigos en un 20%
        if (probabilidad < 20) {
            return true;
        } else {
            return false;
        }
    }
    
    private void cambiarDePosicion(int x, int y) {

        //ACCIONES SI ENTRA A UNA CIUDAD
        if (mapa_batalla[x][y].equalsIgnoreCase(casillaciudad.icono())) {
            POSICION_CIUDAD_X = x;
            POSICION_CIUDAD_Y = y;
            iniciarbatallaenCiudad();
        } //ACCIONES SI ENTRA A UNA CIUDAD RECONQUISTADA
        else if (mapa_batalla[x][y].equalsIgnoreCase(casillaciudad.Reconquistada())) {
            System.out.println("Esta ciudad ha sido Reconquistada");
            player.recuperarVidaYMagias();
        } //ACCIONES SI ENTRA A LA POSADA
        else if (mapa_batalla[x][y].equalsIgnoreCase(casillaPosada.icono())) {
            System.out.println("Has entrado a la posada");
            casillaPosada.INICIAR_POSADA(player);
            
        } //ACCIONES SI ENTRA A LA TIENDA
        else if (mapa_batalla[x][y].equalsIgnoreCase(casillaTienda.icono())) {
            System.out.println("Has entrado a la tienda");
            tienda.INICIAR_TIENDA();
            // REESTRUCTURAR player.getInventario().INICIAR_TIENDA();
        } else {
            devolvercasillaPisada();
            guardarPaso = mapa_batalla[x][y];
            
            if (probabilidadDeAparicionEnemigo()) {
                iniciarbatallaenemigoDebil_Fuerte();
            }
            if (x == posicionX - 1) {
                posicionX--;
            } else if (x == posicionX + 1) {
                posicionX++;
            } else if (y == posicionY - 1) {
                posicionY--;
            } else if (y == posicionY + 1) {
                posicionY++;
            }
        }
    }
    
    private void iniciarbatallaenemigoDebil_Fuerte() {
        if (guardarPaso.equalsIgnoreCase(casillazonadebil.icono())) {
            System.out.println("Se ha encontrado con enemigos de tipo debil");
            h.enterParaContinuar();
            h.limpiarPantalla();
            //IniciarBatallaTipoDebil
            ControladorBatallaEnemigo controladorBatallaEnemigo = new ControladorBatallaEnemigo(player);
            
        } else if (guardarPaso.equalsIgnoreCase(casillazonafuerte.icono())) {
            System.out.println("se ha encontrado con enemigos de tipo fuerte");
            h.enterParaContinuar();
            h.limpiarPantalla();
            //IniciarBatallaTipoFuerte
            ControladorBatallaEnemigo controladorBatallaEnemigo = new ControladorBatallaEnemigo(player);
        }
    }
    
    private void finDeLaPartida() {
        
        player.setTotal_De_Ciudades(TOTAL_DE_CIUDADES);
        player.setReconquistadas(CIUDADES_RECONQUISTADAS);
        
        if (TOTAL_DE_CIUDADES == 0) {
            h.separadorLineas();
            System.out.println("Felicidades " + player.getNombreJugador() + " Reconquistaste Todas Las Ciudades =)");
            h.separadorLineas();
        } else {
            h.separadorLineas();
            System.out.println(player.getNombreJugador() + " Has abandonado la partida");
            h.separadorLineas();
        }
        mostrarMapa();
        h.enterParaContinuar();
    }
    
    public String getNameplayer() {
        return nameplayer;
    }
    
    public void setNameplayer(String nameplayer) {
        this.nameplayer = nameplayer;
    }
}
