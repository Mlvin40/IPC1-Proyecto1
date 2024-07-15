/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rpg_guerrerosdelaluz;

/**
 *
 * @author Phoenix
 */
public abstract class Controladores {

    protected boolean partidaEnCurso = true;
    protected int OroPorVictoria;
    

    public boolean isPartidaEnCurso() {
        return partidaEnCurso;
    }

    public void setPartidaEnCurso(boolean partidaEnCurso) {
        this.partidaEnCurso = partidaEnCurso;
    }

}
