/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spsworld.juegobase.elementos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author DSO
 */
public abstract class Sprite {
    
    protected int x;
    protected int y;
    protected int alto;
    protected int ancho;
    protected Color color;
    protected ContenedorGrafico contenedor;
    //protected BufferedImage imagen

    public Sprite(int x, int y, int ancho, int alto, Color color) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.color = color;
    }

    public abstract void dibujar(Graphics g);

    public boolean verificarColision(Sprite otro)
    {
        // Colision eje X
        boolean collisionX = this.getX() + this.getAncho() >= otro.getX() &&
            this.getX() < otro.getX() + otro.getAncho();

        // Colision eje Y
        boolean collisionY = this.getY() + this.getAlto() >= otro.getY() &&
            this.getY() < otro.getY() + otro.getAlto();

        // Colision solo si ambas son: true
        return collisionX && collisionY;
    }

    public boolean fueraContenedor(int x, int y, int ancho, int alto)
    {
        if(contenedor == null)
            return false;
        
        Rectangle limites = contenedor.obtenerBordes();
        
       
        return !(x >= limites.getX() &
                 y >= limites.getY() & //-25
                 x + ancho <= limites.getX() + limites.getWidth() &
                 y + alto  <= limites.getY() + limites.getHeight());
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    public void setContenedorGrafico(ContenedorGrafico contenedorG) {
        this.contenedor = contenedorG;
    }
}
