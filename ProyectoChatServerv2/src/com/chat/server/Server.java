/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chat.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author pablo
 */
public class Server {

    public static void main(String[] args) {
        int contador = 0;
        Compartido c = new Compartido();
        try (ServerSocket ss = new ServerSocket(6001)) {
            for (;;) {
                contador++;
                Socket s = ss.accept();
                System.out.println("Ha llegado el cliente: " + contador);
                System.out.println(s.toString());
                HiloServidor hs = new HiloServidor(s, contador, c);
                hs.start();
            }
        } catch (Exception ex) {
        }
    }
}
