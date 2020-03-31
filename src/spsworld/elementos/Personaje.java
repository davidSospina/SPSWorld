/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spsworld.elementos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import spsworld.juegobase.elementos.Sprite;
import spsworld.multicast.elements.Jugador;

/**
 *
 * @author DSO
 */
public class Personaje extends Sprite{
    
    public static final int ANCHO_INICIAL = 50;
    public static final int ALTO_INICIAL = 50;
    public static final int CRECIMIENTO = 4;
    
    protected int paso = 10;
    
    private Jugador jugador;
    private String nombre;
    private String id;
    
    private Mundo mundo;
    
    public Personaje(int x, int y, int alto, int ancho, Color color, Mundo m) {
        super(x, y, ancho, alto, color);
        
        //setColor();
        this.jugador = new Jugador(this);
        this.mundo = m;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
    public boolean mover(int direccion)
    {
        int nx = x;
        int ny = y;
        switch(direccion)
        {
            case KeyEvent.VK_W:
                ny -= paso;
            break;

            case KeyEvent.VK_S:
                ny += paso;
            break;

            case KeyEvent.VK_A:
                nx -= paso;
            break;

            case KeyEvent.VK_D:
                nx += paso;
            break;
        }
        
        if(!fueraContenedor(nx, ny, ancho, alto))
        {
            x = nx;
            y = ny;

            if(contenedor != null)
                contenedor.refrescar();
            
            this.jugador.enviarMovimiento(x, y, ancho, alto);
            return true;
        }
        
        return false;
    }
    
    public void enviarMensaje(String mensaje){
        this.jugador.enviarChat(this.id+"::"+this.nombre+"::"+mensaje);
    }
    
    public void enviarInicio(){
        
        String temp = this.id+"::"+this.nombre;
        this.jugador.enviarInicio(temp);
        
    }
    
    public void recibirMensaje(String mensaje){
        this.mundo.interpretar(mensaje);
    }

    public void crecerPersonaje()
    {
        x -= CRECIMIENTO/2;
        y -= CRECIMIENTO/2;
        ancho += CRECIMIENTO;
        alto += CRECIMIENTO;
        
        contenedor.refrescar();
    }
    
    public void encogerPersonaje()
    {
        x += CRECIMIENTO/2;
        y += CRECIMIENTO/2;
        ancho -= CRECIMIENTO;
        alto -= CRECIMIENTO;
        
        contenedor.refrescar();
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, alto, ancho);
    }
}
