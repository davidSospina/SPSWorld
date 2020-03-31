/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spsworld.views;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import spsworld.elementos.Mundo;
import spsworld.juegobase.elementos.ContenedorGrafico;

/**
 *
 * @author andres
 */
public class PanelContainer extends JPanel implements ContenedorGrafico {

    protected Mundo mundo;
    protected ContenedorGrafico contenedor;
    protected JPanel panel;

    public PanelContainer(ContenedorGrafico c,JPanel j) {
        this.contenedor = c;
        this.panel = j;
    }

    public Mundo getMundo() {
        return mundo;
    }

    public void setMundo(Mundo mundo) {
        this.mundo = mundo;
    }

    public void keyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_Q) {
            System.exit(0);
        }
        
        if (evt.getKeyCode() == KeyEvent.VK_W
                | evt.getKeyCode() == KeyEvent.VK_S
                | evt.getKeyCode() == KeyEvent.VK_A
                | evt.getKeyCode() == KeyEvent.VK_D) {
            mundo.keyPressed(evt.getKeyCode());
        }

        if (evt.getKeyCode() == KeyEvent.VK_UP
                | evt.getKeyCode() == KeyEvent.VK_DOWN
                | evt.getKeyCode() == KeyEvent.VK_LEFT
                | evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            mundo.keyPressed(evt.getKeyCode());
        }
    }

    @Override
    public void paint(Graphics g) {
        if (mundo != null) {
            mundo.dibujar(g);
        }
    }

    @Override
    public void refrescar() {
        this.repaint();
        this.contenedor.refrescar();
        this.panel.repaint();
    }

    @Override
    public Rectangle obtenerBordes() {
        return this.getBounds();
    }

}
