/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spsworld.main;

import java.awt.Color;
import static java.awt.SystemColor.window;
import spsworld.elementos.Mundo;
import spsworld.views.MundoView;

/**
 *
 * @author DSO
 */
public class Main {
    public static void main(String[] args){
        Mundo mundo = new Mundo(0, 0, 500, 500, Color.RED);
        
        MundoView mundoView = new MundoView();
        mundoView.setMundo(mundo);
        mundo.setContenedorGrafico(mundoView);
        mundoView.setSize(500, 500);
        mundoView.setTitle("MUNDO SPS");
        mundoView.setLocationRelativeTo(null);
        mundoView.setVisible(true);
    }
    
}
