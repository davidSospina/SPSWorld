/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spsworld.multicast.elements;

import spsworld.elementos.Personaje;

/**
 *
 * @author andres
 */
public class Jugador {
    
    private Personaje personaje;
    private ClienteMulticastSPS cliente;

    public Jugador(Personaje personaje) {
        this.personaje = personaje;
        this.cliente = new ClienteMulticastSPS("224.111.112.113", 5500,this);
    }
    
    public void enviarChat(String mensaje)
    {
        this.cliente.enviarMensaje(mensaje, ClienteMulticastSPS.CHAT);
    }
    
    public void enviarMovimiento(int x, int y, int width, int height){
        //Falta ponerle un identifecador
        String mensaje = this.personaje.getId()+"::"+this.personaje.getNombre()+"::"+x+">"+y+">"+width+">"+height;
        this.cliente.enviarMensaje(mensaje, ClienteMulticastSPS.MOVIMIENTO);
    }
   
    public void enviarInicio(String mensaje){
        this.cliente.enviarMensaje(mensaje, ClienteMulticastSPS.INICIO);
    }
    
    public void recibirMensaje(String mensaje){
        this.personaje.recibirMensaje(mensaje);
    }
    
    
    
}
