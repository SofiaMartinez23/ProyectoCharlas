/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chat.server;

import com.chat.mensajes.Mensaje;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author pablo
 */
public class Compartido {

    LinkedHashMap<String, Socket> mapUsuarios;
    LinkedHashMap<String, String> mapCanales;

    public Compartido() {
        mapUsuarios = new LinkedHashMap();
    }

    public synchronized boolean addCliente(String id, Socket s) {
        if (mapUsuarios.containsKey(id)) {
            return false;
        } else {
            this.mapUsuarios.put(id, s);
            return true;
        }
    }

    public synchronized void removeCliente(String id) {
        this.mapUsuarios.remove(id);
    }
    public synchronized void listarClientes(String grupo, Socket cliente){
        if (!grupo.equals("")) {
            //si existe el grupo.. si no...
        } else {
            String nicks ="\n";
            for (String nick : mapUsuarios.keySet()) {
            
                nicks+= nick+"\n";
               
            }
            Mensaje mList = new Mensaje("Servidor",nicks);
            ObjectOutputStream ob = null;
            try {
                ob = new ObjectOutputStream(cliente.getOutputStream());
                ob.writeObject(mList);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
             
        }
}

    public synchronized void enviarMensajeGeneral(Mensaje m) {
        for (Map.Entry<String, Socket> entry : mapUsuarios.entrySet()) {
            String key = (String) entry.getKey();
            Socket val = (Socket) entry.getValue();
            ObjectOutputStream ob = null;
            if (!key.equals(m.getIdOrigen())) {
               try {
                ob = new ObjectOutputStream(val.getOutputStream());
                ob.writeObject(m);
            } catch (Exception ex) {
                ex.printStackTrace();
            } 
            }
            
        }
    }
}
