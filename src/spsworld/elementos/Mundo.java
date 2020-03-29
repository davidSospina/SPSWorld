/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spsworld.elementos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import spsworld.juegobase.elementos.ContenedorSprite;
import spsworld.juegobase.elementos.Sprite;

/**
 *
 * @author DSO
 */
public class Mundo extends ContenedorSprite{
    
    protected Personaje personaje;
    
    public Mundo(int x, int y, int ancho, int alto, Color color) {
        super(x, y, ancho, alto, color);
        
        personaje = new Personaje((ancho-personaje.ANCHO_INICIAL)/2, 
                                  (alto-personaje.ALTO_INICIAL)/2, 
                                   personaje.ANCHO_INICIAL, personaje.ALTO_INICIAL,
                                   Color.ORANGE);
        personaje.setContenedorGrafico(this);
        
        //this.setColor(Color.RED);
    }
    
    public void keyPressed(int code)
    {
        if(code == KeyEvent.VK_UP |
           code == KeyEvent.VK_DOWN |
           code == KeyEvent.VK_LEFT |
           code == KeyEvent.VK_RIGHT)
        {
            personaje.mover(code);
        }
    }
    
     

    @Override
    public void dibujar(Graphics g) {
        // Dibujando el Mundo      
        g.setColor(color);
        g.fillRect(x, y, ancho, alto);

        // Dibujando Sprites en general
        for(Sprite sprite : sprites)
        {
            sprite.dibujar(g);
        }
        
        // Dibujando jugador
        personaje.dibujar(g);
    }

    @Override
    public void refrescar() {
        if(contenedor != null)
            contenedor.refrescar();
    }

    @Override
    public Rectangle obtenerBordes() {
        return new Rectangle(x, y, ancho, alto);
    }
    
}
