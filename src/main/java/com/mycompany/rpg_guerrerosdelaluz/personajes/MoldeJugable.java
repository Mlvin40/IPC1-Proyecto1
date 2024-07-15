/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rpg_guerrerosdelaluz.personajes;

import com.mycompany.rpg_guerrerosdelaluz.otros.Colores;
import com.mycompany.rpg_guerrerosdelaluz.otros.Herramientas;
import com.mycompany.rpg_guerrerosdelaluz.tipoarmas.Arma;
import com.mycompany.rpg_guerrerosdelaluz.tipostrabajo.DFisicos;
import com.mycompany.rpg_guerrerosdelaluz.tipostrabajo.DMagos;
import com.mycompany.rpg_guerrerosdelaluz.tipostrabajo.Trabajo;
import java.util.Random;

/**
 *
 * @author Phoenix
 */
public abstract class MoldeJugable {

    protected static final String RESET = "\u001B[0m";
    protected static final String AZUL = "\u001B[34m";
    protected static final String ROJO = "\u001B[31m";

    Random random = new Random();

    //Un objeto que contiene los colores y metodo para pintar una frase 
    Colores c = new Colores();

    //Un objeto que contiene diferetes tipo de herramientas para texto
    Herramientas h = new Herramientas();

    private int cantidadDanioTotalRealizado = 0;
    private int cantidadDeMuertes = 0;

    private static final int MAXIMA_CANTIDAD_TRABAJOS = 2;
    protected String nombre;
    protected int nivel;
    protected Trabajo[] trabajosMaximos = new Trabajo[MAXIMA_CANTIDAD_TRABAJOS];
    protected Trabajo trabajoActivo;

    protected boolean conVida;
    protected Arma armaPortada;

    protected double defensaBase;
    protected double defensaTotal;

    protected double concentracionBase;
    protected double concentracionTotal; // daño magico

    protected double espirituBase;
    protected double espirituTotal; // resistencia magica

    protected double velocidadBase;
    protected double velocidadTotal;

    //Todo relacionado a la vida
    protected double puntosdevidaBase;
    protected double puntosdevidaTotal;

    //Para determinar el nivel 
    protected int experiencia;

    //Todo relacionado al daño
    protected double danioBase;
    protected double fuerzaTotal;

    /**
     * Constructor
     * @param nombre
     */
    public MoldeJugable(String nombre) {

        this.nombre = nombre;
        this.experiencia = 0;
        this.nivel = 1;
        this.conVida = true;

        //Todo_Base
        this.defensaBase = random.nextInt(11) + 15; //rango 15-25
        this.concentracionBase = random.nextInt(16) + 50; // Rango 50-65
        this.espirituBase = random.nextInt(11) + 12; // Rango 12-22
        this.velocidadBase = random.nextInt(11) + 15; // Rango 15-25
        this.puntosdevidaBase = random.nextInt(31) + 120; // Rango 120-150
        this.danioBase = random.nextInt(21) + 50; // Rango 50-70

        //Todo_En_Total_Agarra el valor preteminado
        actualizarDaMaHpVeFinales();
    }

    //Para actualizar la estadisticas finales de los guerreos de la luz
    protected void actualizarDaMaHpVeFinales() {
        //Todo_En_Total_Agarra el valor preteminado
        this.defensaTotal = defensaBase;
        this.concentracionTotal = concentracionBase;
        this.espirituTotal = espirituBase;
        this.velocidadTotal = velocidadBase;
        this.puntosdevidaTotal = puntosdevidaBase;
        this.fuerzaTotal = danioBase;
    }

    //Recibe un blanco al que se le restaran los puntos de ataque del jugador en turno
    public void realizarAtaque(MoldeJugable objetivo) {

        if (this.getTrabajoActivo() instanceof DFisicos) {
            recibirdanioFisico(objetivo);
        } else if (this.getTrabajoActivo() instanceof DMagos) {
            recibirdanioMagico(objetivo);
        } else {
            recibirdanioFisico(objetivo);
        }
    }

    //METODO UTILIZADO POR TODOS LOS JUGADORES QUE POSEAN UN TRABAJO DE DAÑO FISICO
    private void recibirdanioFisico(MoldeJugable objetivo) {

        double calcularDanio = this.fuerzaTotal - objetivo.getDefensaTotal();
        double daniorealizado = objetivo.getPuntosdevidaTotal() - calcularDanio;
        objetivo.setPuntosdevidaTotal(daniorealizado);

        cantidadDanioTotalRealizado += calcularDanio;

        if (objetivo.getPuntosdevidaTotal() <= 0) {
            System.out.println(nombre + " ha matado a " + objetivo.getNombre());
            objetivo.setPuntosdevidaTotal(0);
            objetivo.agregarUnaMuerte();
        } else {
            System.out.println(objetivo.getNombre() + " ha reducido " + objetivo.getDefensaTotal() + " puntos de daño con su defensa");
            System.out.println(objetivo.getNombre() + " ha recibido un daño fisico de: " + calcularDanio + " quedando con " + objetivo.getPuntosdevidaTotal() + " puntos de vida");
        }
    }

    //METODO UTILIZADO POR TODOS LOS JUGADORES QUE POSEAN UN TRABAJO DE DAÑO MAGICO
    private void recibirdanioMagico(MoldeJugable objetivo) {
        double calcular_danio_magico = this.concentracionTotal - objetivo.getEspirituTotal();
        double daniorealizado = objetivo.getPuntosdevidaTotal() - calcular_danio_magico;
        objetivo.setPuntosdevidaTotal(daniorealizado);

        cantidadDanioTotalRealizado += calcular_danio_magico;

        if (objetivo.getPuntosdevidaTotal() <= 0) {
            System.out.println(nombre + " ha matado a " + objetivo.getNombre());
            objetivo.setPuntosdevidaTotal(0);
            objetivo.agregarUnaMuerte();
        } else {
            System.out.println(objetivo.getNombre() + " ha reducido " + objetivo.getDefensaTotal() + " puntos de daño con su espiritu");
            System.out.println(objetivo.getNombre() + " ha recibido un daño magico de: " + calcular_danio_magico + " quedando con " + objetivo.getPuntosdevidaTotal() + " puntos de vida");
        }
    }

    public abstract void aumentarEstadisticasPorTrabajo();

    //PARA LA INFORMACION DE CADA GUERRERO O BOT GENERADO
    public void puntosEstadistica() {
        h.separadorLineas();
        System.out.println(c.azul(nombre) + "                     " + c.azul("Nivel: ") + nivel + "                " + c.azul(trabajoActivo.getNombre()));
        System.out.println(c.gris("HP  ") + puntosdevidaTotal + "/" + puntosdevidaBase + "             " + c.gris("Fuerza  ") + fuerzaTotal + "            " + c.gris("EXP  ") + experiencia);
        System.out.println(c.gris("Defensa  ") + defensaTotal + "             " + c.gris("Espiritu  ") + espirituTotal + "          " + c.gris("Velocidad  ") + velocidadTotal);
        System.out.println(c.gris("Concentracion  ") + concentracionTotal + "       " + c.gris("Vivo  ") + conVida + "              " + c.gris("Arma  ") + verSiHayArma());
        h.separadorLineas();
        h.saltoDeLinea();
    }

    //PARA LOS PUNTOS DE ESTADISTICA EN BATALLAS
    public void mostrarEstadisticasEnBatalla() {

        if (estaVivo()) {
            System.out.println(nombre + c.gris("  HP: ") + puntosdevidaTotal + "/" + puntosdevidaBase + c.gris("  Daño: ") + fuerzaTotal + c.gris(" Defensa: ") + defensaTotal
                    + c.gris("  Concentracion: ") + concentracionTotal + c.gris("  Espiritu: ") + espirituTotal + c.gris("  Velocidad: " + velocidadTotal));
        } else {
            System.out.println(nombre + " ~ " + c.rojo("Muerto"));
        }
    }

    private String verSiHayArma() {
        if (trabajoActivo.getArma_en_posesion() != null) {
            return trabajoActivo.getArma_en_posesion().getNombre();
        } else {
            return "No hay";
        }
    }

    public boolean estaVivo() {
        //si esta muerto
        if (puntosdevidaTotal <= 0) {
            conVida = false;
            return conVida;
        } else {
            //si esta vivo
            conVida = true;
            return conVida;
        }
    }

    public void agregarUnaMuerte() {
        cantidadDeMuertes++;
    }

    public void ganarPartida(int experienciaGanada) {

        this.experiencia += experienciaGanada;
        if (this.experiencia >= 100 * this.nivel) {
            subirDeNivel();
        }
    }

    protected void subirDeNivel() {
        this.nivel++;

        // Incrementar características al subir de nivel
        int ganado_por_nivel = 10;

        this.danioBase += ganado_por_nivel;
        this.defensaBase += ganado_por_nivel;
        this.concentracionBase += ganado_por_nivel;
        this.espirituBase += ganado_por_nivel;
        this.velocidadBase += ganado_por_nivel;
        this.puntosdevidaBase += ganado_por_nivel;
        h.separadorLineas();
        System.out.println(nombre + c.gris(" Suve al nivel " + nivel + " y sus estadisticas aumentan en "));
        System.out.println("Daño+" + ganado_por_nivel + " Defensa+" + ganado_por_nivel + " Velocidad+" + ganado_por_nivel + " Concentracion+" + ganado_por_nivel + " Espiritu+" + ganado_por_nivel + " HP+" + ganado_por_nivel);

        trabajoActivo.ganar_estadisticas_por_nivel(this);
        h.saltoDeLinea();

        actualizar_Totales_alSubir_Nivel();

        aumentarEstadisticasPorTrabajo();
    }

    private void actualizar_Totales_alSubir_Nivel() {
        this.defensaTotal = defensaBase;
        this.concentracionTotal = concentracionBase;
        this.espirituTotal = espirituBase;
        this.velocidadTotal = velocidadBase;
        this.fuerzaTotal = danioBase;
    }

    //METODOS GETTERS_AND_SETTERS
    public int getCantidadDe_T_DanioRelizado() {
        return cantidadDanioTotalRealizado;
    }

    public int getCantidadDeMuertes() {
        return cantidadDeMuertes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public Trabajo[] getTrabajosmax() {
        return trabajosMaximos;
    }

    public void setTrabajosmax(Trabajo[] trabajosmax) {
        this.trabajosMaximos = trabajosmax;
    }

    public Trabajo getTrabajoActivo() {
        return trabajoActivo;
    }

    public void setTrabajoActivo(Trabajo trabajoActivo) {
        this.trabajoActivo = trabajoActivo;
    }

    public boolean isConVida() {
        return conVida;
    }

    public void setConVida(boolean conVida) {
        this.conVida = conVida;
    }

    public double getDefensaBase() {
        return defensaBase;
    }

    public void setDefensaBase(double defensaBase) {
        this.defensaBase = defensaBase;
    }

    public double getDefensaTotal() {
        return defensaTotal;
    }

    public void setDefensaTotal(double defensaTotal) {
        this.defensaTotal = defensaTotal;
    }

    public double getVelocidadBase() {
        return velocidadBase;
    }

    public void setVelocidadBase(double velocidadBase) {
        this.velocidadBase = velocidadBase;
    }

    public double getVelocidadTotal() {
        return velocidadTotal;
    }

    public void setVelocidadTotal(double velocidadTotal) {
        this.velocidadTotal = velocidadTotal;
    }

    public double getPuntosdevidaBase() {
        return puntosdevidaBase;
    }

    public void setPuntosdevidaBase(double puntosdevidaBase) {
        this.puntosdevidaBase = puntosdevidaBase;
    }

    public double getPuntosdevidaTotal() {
        return puntosdevidaTotal;
    }

    public void setPuntosdevidaTotal(double puntosdevidaTotal) {
        this.puntosdevidaTotal = puntosdevidaTotal;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    public double getDanioBase() {
        return danioBase;
    }

    public void setDanioBase(double danioBase) {
        this.danioBase = danioBase;
    }

    public double getFuerzaTotal() {
        return fuerzaTotal;
    }

    public void setFuerzaTotal(double fuerzaTotal) {
        this.fuerzaTotal = fuerzaTotal;
    }

    public double getConcentracionBase() {
        return concentracionBase;
    }

    public void setConcentracionBase(double concentracionBase) {
        this.concentracionBase = concentracionBase;
    }

    public double getConcentracionTotal() {
        return concentracionTotal;
    }

    public void setConcentracionTotal(double concentracionTotal) {
        this.concentracionTotal = concentracionTotal;
    }

    public double getEspirituBase() {
        return espirituBase;
    }

    public void setEspirituBase(double espirituBase) {
        this.espirituBase = espirituBase;
    }

    public double getEspirituTotal() {
        return espirituTotal;
    }

    public void setEspirituTotal(double espirituTotal) {
        this.espirituTotal = espirituTotal;
    }
}
