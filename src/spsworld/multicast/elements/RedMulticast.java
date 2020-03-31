/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spsworld.multicast.elements;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import spsworld.elementos.Personaje;

/**
 *
 * @author andres
 */
public class RedMulticast extends Thread{
    
    private MulticastSocket msocket;
    private DatagramPacket recv;
    private ClienteMulticastSPS personaje;

    public RedMulticast(MulticastSocket msocket, ClienteMulticastSPS p) {
        this.msocket = msocket;
        this.personaje = p;
    }
    
    @Override
    public void run()
    {
        byte[] buf = new byte[1000];
        String tmp;
        try
        {
            for(;;)
            {
               // Handle the incoming data and print it to stnd output.
               recv = new DatagramPacket(buf, buf.length);
               msocket.receive(recv);
               tmp = new String(recv.getData(),0,recv.getLength());
               System.out.println("\n\nRecived: \""+ tmp + "\"\nMessage Length is: " + tmp.length());
               /////////////////////
               this.personaje.recibirMensaje(tmp);
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Exit...");
            e.printStackTrace();
        } 
        finally 
        {
            msocket.close();
        }
    }   
    
    
    
}
