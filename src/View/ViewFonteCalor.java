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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class ViewFonteCalor extends JPanel{
    
    private JLabel lblcompressor = new JLabel("Fonte de Calor: ");
    private JLabel lblTf = new JLabel("<html>T<sub>f</sub></html>");
    private JLabel lblPf = new JLabel("<html>P<sub>f</sub></html>");
    private JLabel lblMf = new JLabel("<html>&#7745;<sub>f</sub></html>");
    
    private JTextField txtTf = new JTextField("431.15");
    private JTextField txtPf = new JTextField("1000");
    private JTextField txtMf = new JTextField("100");
    
    private String[] fluidos = {"Água", "Ar", "Compressor 1", "Compressor 2", "Compressor 3", "Compressor 4", "Compressor 5"};
    private JComboBox cmbCompressor = new JComboBox(fluidos);
    
    private String[] pressoes = {"kPa", "bar", "atm"};
    private JComboBox comboPf = new JComboBox(pressoes);

    private String[] temps = {"K", "°C", "°F"};
    private JComboBox comboTf = new JComboBox(temps);
    
    private int comp = 0;
    private int FON = 1;
    
    private ControlConverte controlConverte;
    
    public ViewFonteCalor(ControlPrincipal ctrlPrincipal){
        controlConverte = new ControlConverte();
        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(1, Color.lightGray, Color.lightGray), "Entrada", 1, 2, new Font("Times New Roman", 1, 12), Color.darkGray));
        
        GridBagConstraints g = new GridBagConstraints();
        
        Font font = new Font("Arial", 0, 14);
        lblMf.setFont(font);
        
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(lblcompressor,g);
        
        g.gridx = 1;
        g.gridy = 0;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(cmbCompressor,g);
        
        g.gridx = 0;
        g.gridy = 1;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(lblTf,g);
        
        g.gridx = 1;
        g.gridy = 1;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(txtTf,g);
        
        g.gridx = 3;
        g.gridy = 1;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(comboTf,g);
        
        g.gridx = 0;
        g.gridy = 2;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(lblPf,g);
        
        g.gridx = 1;
        g.gridy = 2;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(txtPf,g);
        
        g.gridx = 3;
        g.gridy = 2;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(comboPf,g);
        
        g.gridx = 0;
        g.gridy = 3;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(lblMf,g);
        
        g.gridx = 1;
        g.gridy = 3;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(txtMf,g);
        
        cmbCompressor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                FON = cmbCompressor.getSelectedIndex()+1;
                switch(FON){
                    case 3:
                        comp = 1;
                        txtMf.setText(55.54+"");
                        txtTf.setText(415.15+"");
                        txtPf.setText(1144.4+"");
                        break;
                    case 4:
                        comp = 2;
                        txtMf.setText(55.54+"");
                        txtTf.setText(417.15+"");
                        txtPf.setText(3133.8+"");
                        break;
                    case 5: 
                        comp = 3;
                        txtMf.setText(55.54+"");
                        txtTf.setText(418.15+"");
                        txtPf.setText(8825.6+"");
                        break;
                    case 6:
                        comp = 4;
                        txtMf.setText(55.54+"");
                        txtTf.setText(408.15+"");
                        txtPf.setText(25109+"");
                        break;
                    case 7: 
                        comp = 5;
                        txtMf.setText(61.78+"");
                        txtTf.setText(431.15+"");
                        txtPf.setText(8196.1+"");
                        break;
                    default:
                        comp = 0;
                        break;
                }
                
                if(comp != 0){
                    txtMf.setEnabled(false);
                    txtTf.setEnabled(false);
                    txtPf.setEnabled(false);
                }else{
                    txtMf.setEnabled(true);
                    txtTf.setEnabled(true);
                    txtPf.setEnabled(true);
                }
                
            }
        });
        
        comboTf.addItemListener(new ItemListener() {
            
            String[] tipo = new String[2];
            int tip = 0;
            
            @Override
            public void itemStateChanged(ItemEvent ie) {
                
                tipo[tip] = ie.getItem().toString();
                tip++;
                if(tip == 2){
                    String valor = txtTf.getText();
                    if(!valor.isEmpty()){
                        txtTf.setText(String.valueOf(controlConverte.converte(tipo[0],tipo[1],Double.parseDouble(valor))));
                    } 
                    tip = 0;
                }
            }
        });
        
        comboPf.addItemListener(new ItemListener() {
            
            String[] tipo = new String[2];
            int tip = 0;
            
            @Override
            public void itemStateChanged(ItemEvent ie) {
                
                tipo[tip] = ie.getItem().toString();
                tip++;
                if(tip == 2){
                    String valor = txtPf.getText();
                    if(!valor.isEmpty()){
                        txtPf.setText(String.valueOf(controlConverte.converte(tipo[0],tipo[1],Double.parseDouble(valor))));
                    } 
                    tip = 0;
                }
            }
        });
    }

    public JLabel getLblcompressor() {
        return lblcompressor;
    }

    public JLabel getLblTf() {
        return lblTf;
    }

    public JLabel getLblPf() {
        return lblPf;
    }

    public JLabel getLblMf() {
        return lblMf;
    }

    public JTextField getTxtTf() {
        return txtTf;
    }

    public JTextField getTxtPf() {
        return txtPf;
    }

    public JTextField getTxtMf() {
        return txtMf;
    }

    public String[] getFluidos() {
        return fluidos;
    }

    public JComboBox getCmbCompressor() {
        return cmbCompressor;
    }

    public String[] getPressoes() {
        return pressoes;
    }

    public JComboBox getComboPf() {
        return comboPf;
    }

    public String[] getTemps() {
        return temps;
    }

    public JComboBox getComboTf() {
        return comboTf;
    }

    public int getComp() {
        return comp;
    }

    public int getFON() {
        return FON;
    }
    
    
}
