/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rpg_guerrerosdelaluz.personajes;

import com.mycompany.rpg_guerrerosdelaluz.tipoarmas.Arma;
import com.mycompany.rpg_guerrerosdelaluz.tipoarmas.ArmaCorta;
import com.mycompany.rpg_guerrerosdelaluz.tipoarmas.ArmaDosManos;
import com.mycompany.rpg_guerrerosdelaluz.tipoarmas.ArmaUnaMano;
import com.mycompany.rpg_guerrerosdelaluz.tipoarmas.Baculo;
import com.mycompany.rpg_guerrerosdelaluz.tipoarmas.Escudo;
import com.mycompany.rpg_guerrerosdelaluz.tipostrabajo.Guerrero;
import com.mycompany.rpg_guerrerosdelaluz.tipostrabajo.MagoBlanco;
import com.mycompany.rpg_guerrerosdelaluz.tipostrabajo.MagoOscuro;
import com.mycompany.rpg_guerrerosdelaluz.tipostrabajo.MagoRojo;
import com.mycompany.rpg_guerrerosdelaluz.tipostrabajo.Ninja;
import com.mycompany.rpg_guerrerosdelaluz.tipostrabajo.Paladin;
import com.mycompany.rpg_guerrerosdelaluz.tipostrabajo.Trabajo;
import java.util.Random;

/**
 *
 * @author Phoenix
 */
public class Bot extends MoldeJugable {

    public Bot() {
        super(ROJO + elegirNombre() + RESET);
        trabajoActivo = trabajoAleatorio();
        //
        armaPortada = armaAleatoria(trabajoActivo);
        //
        trabajoActivo.setArma_en_posesion(armaPortada);
        nivel = nivelAleatorio();

        calcular_estadisticas_por_nivel();
        //Actualizar las estadisticas por el trabajo asignado aleatoriamente
        aumentarEstadisticasPorTrabajo();

        //Como el bot tiene un arma aleatorio se actualizan sus estadisticas dependiendo de cual tenga asignado
        trabajoActivo.getArma_en_posesion().aumentar_estadisticas(this);
    }

    /**
     * Metodo para asignarle un trabajo aleatorio a un bot
     *
     * @return
     */
    private Trabajo trabajoAleatorio() {

        Random random = new Random();
        int numero = random.nextInt(6);

        switch (numero) {
            case 0:
                return new Guerrero();

            case 1:
                return new Ninja();

            case 2:
                return new Paladin();

            case 3:
                return new MagoBlanco();

            case 4:
                return new MagoOscuro();

            case 5:
                return new MagoRojo();

            default:
                throw new AssertionError();
        }
    }

    /**
     * Elije un arma aleatoria en base al trabajo previamente seleccionado
     *
     * @param elegidoPC
     * @return
     */
    private Arma armaAleatoria(Trabajo elegidoPC) {
        Random random = new Random();

        /**
         * PRIMER TRABAJO arma de una mano arma de dos manos
         */
        if (elegidoPC instanceof Guerrero) {
            int numero = random.nextInt(2);

            switch (numero) {
                case 0:
                    return new ArmaUnaMano();

                case 1:
                    return new ArmaDosManos();

                default:
                    throw new AssertionError();
            }
        } /**
         * SEGUNDO TRABAJO arma corta
         */
        else if (elegidoPC instanceof Ninja) {
            return new ArmaCorta();
        } /**
         * TERCER TRABAJO arma una mano escudo
         */
        else if (elegidoPC instanceof Paladin) {
            int numero = random.nextInt(2);

            switch (numero) {
                case 0:
                    return new ArmaUnaMano();

                case 1:
                    return new Escudo();

                default:
                    throw new AssertionError();
            }
        } /**
         * CUARTO TRABAJO baculo
         */
        else if (elegidoPC instanceof MagoBlanco) {
            return new Baculo();
        } /**
         * QUINTO TRABAJO baculo
         */
        else if (elegidoPC instanceof MagoOscuro) {
            return new Baculo();

            /**
             * SEXTO TRABAJO arma una mano
             */
        } else if (elegidoPC instanceof MagoRojo) {

            return new ArmaUnaMano();
        }

        throw new AssertionError();
    }

    private int nivelAleatorio() {
        Random random = new Random();
        int numero = random.nextInt(3);

        switch (numero) {
            case 0:
                return 1;

            case 1:
                return 2;

            case 2:
                return 3;

            default:
                throw new AssertionError();
        }
    }

    public void detallesBot() {
        System.out.println(nombre);
        System.out.println("Trabajo: " + trabajoActivo.getNombre());
        System.out.println("Arma: " + armaPortada.getNombre());
        System.out.println("NivelDelBot: " + nivel);
    }

    @Override
    public void aumentarEstadisticasPorTrabajo() {
        trabajoActivo.actualizarEstadisticas(this);
    }

    @Override
    protected void subirDeNivel() {

        this.danioBase += 5;
        this.defensaBase += 5;
        this.concentracionBase += 5;
        this.espirituBase += 5;
        this.velocidadBase += 5;
        this.puntosdevidaBase += 5;

        actualizarDaMaHpVeFinales();
        aumentarEstadisticasPorTrabajo();

    }

    public void calcular_estadisticas_por_nivel() {

        if (nivel == 1) {
            //no aumenta estadisticas   
        } else {
            for (int i = 0; i < nivel; i++) {
                subirDeNivel();
            }
        }
    }

    private static String elegirNombre() {

        String[] nameTags = {
            "Shadowblade", "Nightreaper", "Darkstorm", "Grimshade", "Soulrender",
            "Duskbringer", "Voidwalker", "Deathbringer", "Dreadshadow", "Abyssal",
            "Necroshade", "Gloomblade", "Darkthorn", "Twilightbane", "Acheron",
            "Eclipsar", "Nocturne", "Obsidian", "Amarok", "Blightfang",
            "Blackheart", "Shadowfang", "Darkslayer", "Nightfall", "Dreadblade",
            "Darkfire", "Voidreaver", "Shadowrend", "Nightstalker", "Grimmaw",
            "Duskraven", "Soulshatter", "Abysswalker", "Necrosoul", "Gloomsoul",
            "Darkheart", "Twilightshroud", "Acheron", "Eclipsar", "Nocturne",
            "Obsidian", "Amarok", "Blightfang", "Blackheart", "Shadowfang",
            "Darkslayer", "Nightfall", "Dreadblade", "Bloodshadow", "Darkblight",
            "Midnightreaper", "Shadowstalker", "Nightbringer", "Darkbane",
            "Shadowfiend", "Grimdark", "Shadowfury", "Nightcloak", "Voidspawn",
            "Darkwrath", "Shadowclaw", "Nightwhisper", "Darkhowl", "Shadowfell",
            "Grimshade", "Darkblaze", "Nightdrifter", "Shadowflame"
        };

        Random random = new Random();
        int seleccionado = random.nextInt(nameTags.length);

        // Devolver el nombre aleatorio seleccionado
        return nameTags[seleccionado];
    }
}
