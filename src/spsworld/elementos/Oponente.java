/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spsworld.elementos;

import java.awt.Color;
import java.awt.Graphics;
import static spsworld.elementos.Personaje.CRECIMIENTO;
import spsworld.juegobase.elementos.Sprite;

/**
 *
 * @author andres
 */
public class Oponente extends Sprite{
    
    private Color[] colores = null;
    
    private String id;
    private String nombre;
    

    public Oponente(int x, int y, int ancho, int alto, String id, String nombre){
        super(x, y, ancho, alto, Color.BLACK);
        
        this.colores = new Color[10];
        seleccionarColor();
        
        this.id = id;
        this.nombre = nombre;
        
         
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
    
    
    
    public void generarColores(){
        this.colores[0]= Color.YELLOW;
        this.colores[1]= Color.BLACK;
        this.colores[2]= Color.CYAN;
        this.colores[3]= Color.GRAY;
        this.colores[4]= Color.PINK;
        this.colores[5]= Color.GREEN;
        this.colores[6]= Color.BLUE;
        this.colores[7]= Color.DARK_GRAY;
        this.colores[8]= Color.LIGHT_GRAY;
        this.colores[9]= Color.MAGENTA;
    }
    
    public final void seleccionarColor(){
        this.generarColores();
        int indice =(int) (Math.random() * 9);
        
        Color color = colores[indice];
        this.setColor(color);
        
    }
    
    public void mover(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.ancho = width;
        this.alto = height;
              
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
