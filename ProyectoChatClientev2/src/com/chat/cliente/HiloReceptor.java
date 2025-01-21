/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chat.cliente;

import com.chat.mensajes.Mensaje;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 *
 * @author pablo
 */
public class HiloReceptor extends Thread{
    
    Socket s;

    public HiloReceptor(Socket s){
        this.s = s;
    }
    
    public void run(){
        Mensaje m;
        ObjectInputStream ob = null;
        try{
            do {
                ob = new ObjectInputStream(s.getInputStream());
                m = (Mensaje) ob.readObject();
                System.out.println(m.getIdOrigen() + ": " + m.getTexto());
                System.out.println("-> ");
            } while (true);
        } catch (Exception ex) {
        }
    }
}
