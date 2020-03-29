/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spsworld.juegobase.elementos;

import java.awt.Color;

/**
 *
 * @author DSO
 */
public abstract class SpriteMovil extends Sprite{
    protected int paso;
    
    public SpriteMovil(int x, int y, int ancho, int alto, Color color) {
        super(x, y, ancho, alto, color);
    }

    public int getPaso() {
        return paso;
    }

    public void setPaso(int paso) {
        this.paso = paso;
    }
}
