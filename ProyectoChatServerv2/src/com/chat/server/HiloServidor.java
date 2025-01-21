/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chat.server;

import com.chat.mensajes.Mensaje;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pablo
 */
public class HiloServidor extends Thread {

    Socket cliente;
    String nickname;
    int id;
    Compartido c;

    public HiloServidor(Socket cliente, int id, Compartido c) {
        this.cliente = cliente;
        this.id = id;
        this.c = c;
    }

    public void run() {
        Mensaje m;
        boolean nicknameValido;
        ObjectInputStream ob = null;
        ObjectOutputStream obs = null;
        try {
            do {
                ob = new ObjectInputStream(this.cliente.getInputStream());
                m = (Mensaje) ob.readObject();
                this.nickname = m.getIdOrigen();
                nicknameValido = c.addCliente(nickname, cliente);
                obs = new ObjectOutputStream(this.cliente.getOutputStream());
                if(nicknameValido == true){
                    m = new Mensaje("","OK");
                }else{
                    m = new Mensaje("","KO");
                }
                obs.writeObject(m);
            } while (nicknameValido == false);
            
            do {
                ob = new ObjectInputStream(this.cliente.getInputStream());
                m = (Mensaje) ob.readObject();
                String mensaje = m.getTexto();
                String [] partesMensaje = mensaje.split(" ",3);
                System.out.println("Recibido: " + mensaje + " ------ " + this.nickname);
                if (!partesMensaje[0].equals(".exit")) {
                    m.setIdOrigen(this.nickname);
                    if (m.getTexto().equals(".help")) {
                        
                    } else if (partesMensaje[0].equals(".list")){
                        
//                        if (partesMensaje[1].equals(mensaje)) {
//                            
//                        }
                        this.c.listarClientes("",this.cliente);
                    } else{ // join + grupo \ exit + grupo
                    this.c.enviarMensajeGeneral(m);
                    }
                }
            } while (!m.getTexto().equals(".exit"));
            System.out.println("Cierre controlado del cliente: " +this.nickname);
        } catch (Exception ex) {
            System.out.println("Cierre abruto de: " + this.nickname);
        } finally {
            c.removeCliente(this.nickname);

            if (ob != null) {
                try {
                    ob.close();
                } catch (Exception ex) {
                }
            }
        }
        try {
            cliente.close();
        } catch (Exception ex) {
        }
    }
}
