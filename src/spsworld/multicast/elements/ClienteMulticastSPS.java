/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spsworld.multicast.elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andres
 */
public class ClienteMulticastSPS {

    private String multicastAddress;
    private int port;
    private RedMulticast red;
    private MulticastSocket socket;
    private InetAddress grupo;

    public static final int INICIO = 0;
    public static final int CHAT = 1;
    public static final int MOVIMIENTO = 2;
    
    private Jugador jugador;

    public ClienteMulticastSPS(String multicastAddress, int port, Jugador j) {
        this.multicastAddress = multicastAddress;
        this.port = port;
        
        this.jugador = j;

        conexion();
    }

    public String getMulticastAddress() {
        return multicastAddress;
    }

    public void setMulticastAddress(String multicastAddress) {
        this.multicastAddress = multicastAddress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    //////////////////////////////////////////////////////////////////////////
    public final void conexion() {
        try {
            this.grupo = InetAddress.getByName(multicastAddress);
            this.socket = new MulticastSocket(port);

            socket.setBroadcast(false);
            System.out.println("Broadcast is: " + socket.getBroadcast());

            socket.setLoopbackMode(false);
            System.out.println("LoopbackMode is: " + socket.getLoopbackMode());

            socket.setTimeToLive(2);
            System.out.println("TimeToLive is: " + socket.getTimeToLive());

            socket.joinGroup(grupo);

            System.out.println("Joined Group: " + multicastAddress + " Port:" + port);

            this.red = new RedMulticast(socket, this);
            this.red.start();

        } catch (Exception err) {
            System.err.println("ERR: Can not join the group " + err);
            err.printStackTrace();
            System.exit(1);
        }

    }

    public void enviarMensaje(String mensaje, int opcion) {

        String line = "";
        switch (opcion) {
            case CHAT:
                line = "CHAT::"+mensaje;
                break;
            case MOVIMIENTO:
                line = "MOVIMIENTO::"+mensaje;
                break;
            case INICIO:
                line = "INICIO::"+mensaje;
                break;
            default:
                break;
        }
        
        DatagramPacket dp = new DatagramPacket(line.getBytes(), line.length(), grupo, port);

        try {
            socket.send(dp);
        } catch (IOException ex) {
            System.out.println("Error al enviar mensaje");
        }
    }
    
    public void recibirMensaje(String mensaje){
        this.jugador.recibirMensaje(mensaje);
    }

}
