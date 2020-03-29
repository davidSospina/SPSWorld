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

/**
 *
 * @author DSO
 */
public class Personaje extends Sprite{
    
    public static final int ANCHO_INICIAL = 50;
    public static final int ALTO_INICIAL = 50;
    public static final int CRECIMIENTO = 4;
    
    protected int paso = 10;
    
    public Personaje(int x, int y, int alto, int ancho, Color color) {
        super(x, y, ancho, alto, color);
        
        //setColor();
    }
    
    public boolean mover(int direccion)
    {
        int nx = x;
        int ny = y;
        switch(direccion)
        {
            case KeyEvent.VK_UP:
                ny -= paso;
            break;

            case KeyEvent.VK_DOWN:
                ny += paso;
            break;

            case KeyEvent.VK_LEFT:
                nx -= paso;
            break;

            case KeyEvent.VK_RIGHT:
                nx += paso;
            break;
        }
        
        if(!fueraContenedor(nx, ny, ancho, alto))
        {
            x = nx;
            y = ny;

            if(contenedor != null)
                contenedor.refrescar();
            
            return true;
        }
        
        return false;
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
