/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spsworld.juegobase.elementos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author DSO
 */
public abstract class ContenedorSprite extends Sprite implements ContenedorGrafico{
    protected ArrayList<Sprite> sprites;   
    
    public ContenedorSprite(int x, int y, int ancho, int alto, Color color) {
        super(x, y, ancho, alto, color);
        
        sprites = new ArrayList<Sprite>();
    }   
    
    public boolean agregarSprite(Sprite sprite)
    {
        return sprites.add(sprite);
    }
    
    public void removerSprite(int index)
    {
        sprites.remove(index);
    }

    public void removerSprite(Sprite sprite)
    {
        sprites.remove(sprite);
    }
    
    public int obtenerCantidad()
    {
        return sprites.size();
    }
}
