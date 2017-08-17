/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author leonardo
 */
public class ViewInicio {

    private JFrame viewInicio;
    private JPanel painelInicio;
    
    public ViewInicio() {
    		viewInicio = new JFrame();
        painelInicio = new JPanel(new BorderLayout());
        
        JLabel l = new JLabel("\n\nAguarde...");
        l.setFont(new Font("TimesRoman",Font.BOLD,50));
        
        l.setHorizontalAlignment(JLabel.CENTER);
        l.setVerticalAlignment(JLabel.CENTER);
        
        painelInicio.add(l);
        painelInicio.setBorder(BorderFactory.createLineBorder(Color.black));
        
        viewInicio.add(painelInicio);
        
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
