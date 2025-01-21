/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chat.mensajes;

import java.io.Serializable;

/**
 *
 * @author pablo
 */
public class Mensaje implements Serializable{
    private String idOrigen;
    private String texto;

    public Mensaje(String idOrigen, String texto) {
        this.idOrigen = idOrigen;
        this.texto = texto;
    }

    public String getIdOrigen() {
        return idOrigen;
    }

    public void setIdOrigen(String idOrigen) {
        this.idOrigen = idOrigen;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
