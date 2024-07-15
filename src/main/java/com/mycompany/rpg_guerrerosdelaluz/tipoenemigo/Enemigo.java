/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rpg_guerrerosdelaluz.tipoenemigo;

import com.mycompany.rpg_guerrerosdelaluz.otros.Colores;
import com.mycompany.rpg_guerrerosdelaluz.personajes.MoldeJugable;
import java.util.Random;

/**
 *
 * @author Phoenix
 */
public class Enemigo extends MoldeJugable {

    Colores c = new Colores();

    Random random = new Random();

    public Enemigo(String nombre) {
        super(ROJO + nombre + RESET);

        super.danioBase = random.nextInt(11) + 25; // Rango 25-35
        super.espirituBase = random.nextInt(6) + 10; // Rango 10-15
        super.concentracionBase = random.nextInt(11) + 25; // Rango 25-35
        super.puntosdevidaBase = random.nextInt(19) + 82; // Rango 82-100
        super.defensaBase = random.nextInt(6) + 10; // Rango 10-15
        super.conVida = true;

        actualizarDaMaHpVeFinales();
    }

    public void detallesBot() {
        System.out.println(nombre);
        System.out.println("Daño: " + fuerzaTotal);
        System.out.println("Magia: " + concentracionTotal);
        System.out.println("HP: " + puntosdevidaTotal);
    }

    //Ataque de los enemigos fijos hacia los guerreros de la luz 
    @Override
    public void realizarAtaque(MoldeJugable objetivo) {
        double daniorealizado = objetivo.getPuntosdevidaTotal() - fuerzaTotal;
        objetivo.setPuntosdevidaTotal(daniorealizado);
        if (objetivo.getPuntosdevidaTotal() <= 0) {

            System.out.println(nombre + " ha matado a " + objetivo.getNombre());
            objetivo.setPuntosdevidaTotal(0);

        } else {
            System.out.println(objetivo.getNombre() + " ha recibido un daño de: " + fuerzaTotal);
        }

    }

    @Override
    public void aumentarEstadisticasPorTrabajo() {
        System.out.println("Los enemigos no tienen trabajos");
    }

    @Override
    public void mostrarEstadisticasEnBatalla() {

        if (estaVivo()) {
            System.out.println(nombre + c.gris("  HP: ") + puntosdevidaTotal + "/" + puntosdevidaBase + c.gris("  Daño: ") + fuerzaTotal + c.gris(" Defensa: ") + defensaTotal
                    + c.gris("  Concentracion: ") + concentracionTotal + c.gris("  Espiritu: ") + espirituTotal);
        } else {
            System.out.println(nombre + " ~ " + c.rojo("Muerto"));
        }
    }

    protected static String elegirNombre() {

        String[] E_nameTags = {
            "Goblin", "Orc", "Troll", "Giant", "Skeleton",
            "Zombie", "Wraith", "Specter", "Ghost", "Ghoul",
            "Vampire", "Werewolf", "Banshee", "Mummy", "Demon",
            "Imp", "Serpent", "Cyclops", "Dragon", "Wyvern",
            "Harpy", "Minotaur", "Centaur", "Golem", "Satyr",
            "Basilisk", "Hobgoblin", "Lich", "Kraken", "Hydra",
            "Chimera", "Cockatrice", "Doppelganger", "Elemental", "Fairy",
            "Gargoyle", "Griffin", "Hellhound", "Kobold", "Lamia",
            "Mermaid", "Naga", "Phoenix", "Siren", "Sprite",
            "Treant", "Tengu", "Wendigo", "Yeti", "Zephyr",
            "Scorpion", "Djinn", "Ettin", "Frost Giant", "Fire Elemental",
            "Gnoll", "Lizardfolk", "Medusa", "Oni", "Pegasus",
            "Roc", "Salamander", "Triton", "Umber Hulk", "Valkyrie",
            "Wraith", "Xorn", "Yuan-Ti", "Zaratan"
        };

        Random random = new Random();
        int seleccionado = random.nextInt(E_nameTags.length);

        // Devolver el nombre aleatorio seleccionado
        return E_nameTags[seleccionado];
    }
}
