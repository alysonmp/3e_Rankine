/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Control.ControlPrincipal;
import Control.Conversao.ControlConverte;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author leonardo
 */
public class ViewDadosOperacionais {
    
    private JPanel painelDadosOp = new JPanel(new GridBagLayout());
    
    private JLabel lblP1 = new JLabel("P1: ");
    private JLabel lblT1 = new JLabel("T1: ");
    private JLabel lblSUP = new JLabel("SUP: ");
    private JLabel lblTeff = new JLabel("Teff: ");
    private JLabel lblBeff = new JLabel("Beff: ");
    private JLabel lblTcon = new JLabel("Tcon: ");
    
    private JTextField txtP1 = new JTextField("1229", 10);
    private JTextField txtT1 = new JTextField("413.81");
    private JTextField txtSUP = new JTextField("5");
    private JTextField txtBeff = new JTextField("0.8");
    private JTextField txtTeff = new JTextField("0.8");
    private JTextField txtTcon = new JTextField("0.8");
    
    private String[] pressoes = {"kPa", "bar", "atm"};
    private String[] temps = {"K", "°C", "°F"};
    private JComboBox comboP1 = new JComboBox(pressoes);
    private JComboBox comboT1 = new JComboBox(temps);
    
    private ControlConverte controlConverte;

    public ViewDadosOperacionais(ControlPrincipal ctrlPrincipal) {
        controlConverte = new ControlConverte();
        
        painelDadosOp.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(1, Color.lightGray, Color.lightGray), "Entrada", 1, 2, new Font("Times New Roman", 1, 12), Color.darkGray));
        
        GridBagConstraints g = new GridBagConstraints();
        
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelDadosOp.add(lblT1,g);
        
        g.gridx = 1;
        g.gridy = 0;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelDadosOp.add(txtT1,g);
        
        g.gridx = 3;
        g.gridy = 0;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelDadosOp.add(comboT1,g);
        
        g.gridx = 0;
        g.gridy = 1;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelDadosOp.add(lblP1,g);
        
        g.gridx = 1;
        g.gridy = 1;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelDadosOp.add(txtP1,g);
        
        g.gridx = 3;
        g.gridy = 1;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelDadosOp.add(comboP1,g);
        
        g.gridx = 0;
        g.gridy = 2;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelDadosOp.add(lblSUP,g);

        g.gridx = 1;
        g.gridy = 2;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelDadosOp.add(txtSUP,g);
        
        g.gridx = 0;
        g.gridy = 3;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelDadosOp.add(lblTeff,g);
        
        g.gridx = 1;
        g.gridy = 3;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelDadosOp.add(txtTeff,g);
        
        g.gridx = 0;
        g.gridy = 4;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelDadosOp.add(lblBeff,g);
        
        g.gridx = 1;
        g.gridy = 4;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelDadosOp.add(txtBeff,g);
        
        g.gridx = 0;
        g.gridy = 5;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelDadosOp.add(lblTcon,g);
        
        g.gridx = 1;
        g.gridy = 5;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelDadosOp.add(txtTcon,g);
        
        comboT1.addItemListener(new ItemListener() {
            
            String[] tipo = new String[2];
            int tip = 0;
            
            @Override
            public void itemStateChanged(ItemEvent ie) {
                
                tipo[tip] = ie.getItem().toString();
                tip++;
                if(tip == 2){
                    String valor = txtT1.getText();
                    if(!valor.isEmpty()){
                        txtT1.setText(String.valueOf(controlConverte.converte(tipo[0],tipo[1],Double.parseDouble(valor))));
                    } 
                    tip = 0;
                }
            }
        });
        
        comboP1.addItemListener(new ItemListener() {
            
            String[] tipo = new String[2];
            int tip = 0;
            
            @Override
            public void itemStateChanged(ItemEvent ie) {
                
                tipo[tip] = ie.getItem().toString();
                tip++;
                if(tip == 2){
                    String valor = txtP1.getText();
                    if(!valor.isEmpty()){
                        txtP1.setText(String.valueOf(controlConverte.converte(tipo[0],tipo[1],Double.parseDouble(valor))));
                    } 
                    tip = 0;
                }
            }
        });
    }

    public JPanel getPainelDadosOp() {
        return painelDadosOp;
    }

    public JLabel getLblP1() {
        return lblP1;
    }

    public JLabel getLblT1() {
        return lblT1;
    }

    public JLabel getLblSUP() {
        return lblSUP;
    }

    public JLabel getLblTeff() {
        return lblTeff;
    }

    public JLabel getLblBeff() {
        return lblBeff;
    }

    public JLabel getLblTcon() {
        return lblTcon;
    }

    public JTextField getTxtP1() {
        return txtP1;
    }

    public JTextField getTxtT1() {
        return txtT1;
    }

    public JTextField getTxtSUP() {
        return txtSUP;
    }

    public JTextField getTxtBeff() {
        return txtBeff;
    }

    public JTextField getTxtTeff() {
        return txtTeff;
    }

    public JTextField getTxtTcon() {
        return txtTcon;
    }

    public String[] getPressoes() {
        return pressoes;
    }

    public String[] getTemps() {
        return temps;
    }

    public JComboBox getComboP1() {
        return comboP1;
    }

    public JComboBox getComboT1() {
        return comboT1;
    }
}
