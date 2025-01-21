/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chat.cliente;

import com.chat.mensajes.Mensaje;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author dev
 */
public class Clientev2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String texto, nickname;
        Mensaje m;
        ObjectOutputStream ob = null;
        ObjectInputStream ois = null;
        try(Socket s = new Socket("192.168.11.33",6001);){
            do {
                System.out.print("Introduce el nickname: ");
                nickname = sc.nextLine();
                ob = new ObjectOutputStream (s.getOutputStream());
                ob.writeObject(new Mensaje(nickname,""));
                ois = new ObjectInputStream (s.getInputStream());
                texto = ((Mensaje)ois.readObject()).getTexto();
                if (texto.equals("KO")) {
                    System.out.println("Nickname ya utilizado");
                }
            } while (texto.equals("KO"));
            System.out.println("Bienvenido " + nickname);
            HiloReceptor hr = new HiloReceptor(s);
            hr.start();
            do {
                System.out.println("-> ");
                texto = sc.nextLine();
                m = new Mensaje(nickname,texto);
                ob = new ObjectOutputStream(s.getOutputStream());
                ob.writeObject(m);
            } while (!texto.equals(".exit"));
            System.out.println("Has abandonado el chat... Adios!!");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally{
            if(ob != null){
                try{
                    ob.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
