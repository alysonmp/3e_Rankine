/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author leonardo
 */
public class ViewInicio {

    private JFrame viewInicio;
    
    public ViewInicio() {
        viewInicio = new JFrame();

        JLabel l = new JLabel("       Aguarde...");
        l.setFont(new Font("TimesRoman",Font.BOLD,50));
        
        viewInicio.add(l);
        
        
        viewInicio.setUndecorated(true);
        viewInicio.setSize(400,200);
        viewInicio.setLocationRelativeTo(null);
        viewInicio.setResizable(false);
        viewInicio.setVisible(true);
        
    }

    public JFrame getViewInicio() {
        return viewInicio;
    }
 
}
