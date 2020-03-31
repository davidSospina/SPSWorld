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
import spsworld.views.ViewController;

/**
 *
 * @author DSO
 */
public class Mundo extends ContenedorSprite {

    protected Personaje personaje;

    //////////////////////////////
    private ViewController vista;
    /////////////////////////////

    public Mundo(int x, int y, int ancho, int alto, Color color) {
        super(x, y, ancho, alto, color);

        personaje = new Personaje((ancho - personaje.ANCHO_INICIAL) / 2,
                (alto - personaje.ALTO_INICIAL) / 2,
                personaje.ANCHO_INICIAL, personaje.ALTO_INICIAL,
                Color.ORANGE, this);
        personaje.setContenedorGrafico(this);

        //this.setColor(Color.RED);
    }

    public void setVista(ViewController vista) {
        this.vista = vista;
    }

    public void setNombrePersonaje(String nombre) {
        this.personaje.setNombre(nombre);
    }

    public void setIdPersonaje(String id) {
        this.personaje.setId(id);
    }

    public void interpretar(String comando) {
        switch (comando.split("::")[0]) {

            case "MOVIMIENTO":
                if(!comando.split("::")[1].equals(this.personaje.getId())){
                    Oponente oponente = this.buscarOponente(comando.split("::")[1]);
                    String id = comando.split("::")[1];
                    String nombre = comando.split("::")[2];
                    int xO = Integer.parseInt(comando.split("::")[3].split(">")[0]);
                    int yO = Integer.parseInt(comando.split("::")[3].split(">")[1]);
                    int wO = Integer.parseInt(comando.split("::")[3].split(">")[2]);
                    int hO = Integer.parseInt(comando.split("::")[3].split(">")[3]);
                    
                    if(oponente != null){
                        oponente.mover(xO, yO, wO, hO);
                        this.refrescar();
                    }else{
                        agregarOponente(new Oponente(xO, yO, wO, hO, id, nombre));
                        this.refrescar();
                    }
                }
                break;

            case "INICIO":
                System.out.println("Ayyy me inicie");
                if (!comando.split("::")[1].equals(this.personaje.getId())) {
                    String id = comando.split("::")[1];
                    String nombre = comando.split("::")[2];
                    int x = (ancho - personaje.ANCHO_INICIAL) / 2;
                    int y = (alto - personaje.ALTO_INICIAL) / 2;
                    agregarOponente(new Oponente(x, y, personaje.ANCHO_INICIAL, personaje.ALTO_INICIAL, id, nombre));
                    this.refrescar();
                }
                break;

            case "CHAT":
                System.out.println("Ayyy me chatearon");
                String chat = "+ " + comando.split("::")[2] + " : " + comando.split("::")[3];
                this.vista.mostrarChat(chat);
                break;

        }
    }

    public void agregarOponente(Oponente op) {
        this.vista.mostrarAmigos(op.getNombre());
        this.agregarSprite(op);
    }

    public Oponente buscarOponente(String id) {
        for (int i = 0; i < sprites.size(); i++) {
            Oponente oponente = (Oponente) sprites.get(i);

            if (oponente.getId().equals(id)) {
                return oponente;
            }

        }

        return null;
    }

    public void keyPressed(int code) {
        if (code == KeyEvent.VK_UP
                | code == KeyEvent.VK_DOWN
                | code == KeyEvent.VK_LEFT
                | code == KeyEvent.VK_RIGHT) {
            personaje.mover(code);
        }

        if (code == KeyEvent.VK_W
                | code == KeyEvent.VK_S
                | code == KeyEvent.VK_A
                | code == KeyEvent.VK_D) {
            personaje.mover(code);
        }
    }

    public void enviarChat(String mensaje) {
        this.personaje.enviarMensaje(mensaje);
    }

    public void enviarInicio() {
        this.personaje.setId(generadId());
        this.personaje.enviarInicio();
    }

    public String generadId() {
        long id = (long) (Math.random() * 1854) + 1;
        return id + "";
    }

    @Override
    public void dibujar(Graphics g) {
        // Dibujando el Mundo      
        g.setColor(color);
        g.fillRect(x, y, ancho, alto);

        // Dibujando Sprites en general
        sprites.forEach((sprite) -> {
            sprite.dibujar(g);
        });

        // Dibujando jugador
        personaje.dibujar(g);
    }

    @Override
    public void refrescar() {
        if (contenedor != null) {
            contenedor.refrescar();
        }
    }

    @Override
    public Rectangle obtenerBordes() {
        return new Rectangle(x, y, ancho, alto);
    }

}
